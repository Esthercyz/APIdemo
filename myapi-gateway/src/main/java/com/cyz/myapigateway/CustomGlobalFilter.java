package com.cyz.myapigateway;
import com.cyz.myapiclientsdk.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author YuziChen
 */
@Configuration
@Slf4j
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    private static final List<String> IP_WHITE_LIST = Arrays.asList("127.0.0.1");

    /**
     * 全局过滤
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //1.请求日志
        ServerHttpRequest request = exchange.getRequest();
        String id= request.getId();
        String value = request.getPath().value();
        String methodName = request.getMethod().name();
        String hostAddress = Objects.requireNonNull(request.getRemoteAddress())
                .getAddress().getHostAddress();
        String sourceAddress=request.getLocalAddress().getHostString();
        log.info("请求日志: request-id:{}, uri:{}, method:{}, host address:{},请求来源地址:{}", id, value, methodName, hostAddress,sourceAddress);
        //2.黑白名单
        ServerHttpResponse response = exchange.getResponse();
        if(!IP_WHITE_LIST.contains(sourceAddress)){
            return handleNoAuth(response);
        }
        //3.鉴权（判断ak,sk是否合法）
        HttpHeaders headers=request.getHeaders();
        String accessKey = headers.getFirst("accessKey");
        String nonce = headers.getFirst("nonce");
        String timestamp = headers.getFirst("timestamp");
        String sign = headers.getFirst("sign");
        String body = headers.getFirst("body");
        // todo 实际情况是去数据库中查找是否已经分配给用户
        if(!"cyz".equals(accessKey)){
            return noAuthAns(response);
        }
        if(Long.parseLong(nonce)>10000L){
            return noAuthAns(response);
        }
        //校验时间戳如果时间差距大于 3 分钟报错
        if (!isWithinThreeMinutes(timestamp)) {
            return noAuthAns(response);
        }
        //todo 实际情况是从数据库中查出secretKey
        String serverSign= SignUtils.getSign(body,"abcdefgh");
        if(!sign.equals(serverSign)){
            return noAuthAns(response);
        }
        //4.请求的模拟接口是否存在
        //todo 从数据库中查询模拟接口是否存在，以及请求方法是否匹配，校验请求参数
        //5 - 如果鉴权并且参数校验通过，请求转发，调用模拟接口
        Mono<Void> filter = chain.filter(exchange);
        //6 - 响应日志
        return handleResponse(exchange,chain);

    }

    @Override
    public int getOrder() {
        return -1;
    }

    public Mono<Void> handleResponse(ServerWebExchange exchange, GatewayFilterChain chain) {
        try {
            ServerHttpResponse originalResponse = exchange.getResponse();
            //缓存数据
            DataBufferFactory bufferFactory = originalResponse.bufferFactory();
            //拿到响应码
            HttpStatus statusCode = (HttpStatus) originalResponse.getStatusCode();

            if (statusCode == HttpStatus.OK) {
                ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {

                    @Override
                    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                        log.info("body instanceof Flux: {}", (body instanceof Flux));
                        if (body instanceof Flux) {
                            Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                            //往返回值里写数据，拼接字符串

                            return super.writeWith(
                                    fluxBody.map(dataBuffer -> {
                                        //7 - 调用成功，接口调用次数 + 1
                                        // TODO - 使用 AOP 机制完成接口调用次数更新！
                                byte[] content = new byte[dataBuffer.readableByteCount()];
                                dataBuffer.read(content);
                                DataBufferUtils.release(dataBuffer);//释放掉内存
                                // 构建日志
                                StringBuilder sb2 = new StringBuilder(200);
                                sb2.append("<--- {} {} \n");
                                List<Object> rspArgs = new ArrayList<>();
                                rspArgs.add(originalResponse.getStatusCode());
                                //rspArgs.add(requestUrl);
                                String data = new String(content, StandardCharsets.UTF_8);//data
                                sb2.append(data);
                                //打印日志
                                log.info("响应结果是"+data);
                                log.info(sb2.toString(), rspArgs.toArray());//log.info("<-- {} {}\n", originalResponse.getStatusCode(), data);
                                return bufferFactory.wrap(content);
                            })
                            );
                        } else {
                            // 8 - 调用失败，返回一个规范的错误码
                            log.error("<--- {} 响应code异常", getStatusCode());
                        }
                        return super.writeWith(body);
                    }
                };
                //设置response为装饰过的新对象
                return chain.filter(exchange.mutate().response(decoratedResponse).build());
            }
            return chain.filter(exchange);//降级处理返回数据
        } catch (Exception e) {
            log.error("网关处理响应异常" + e);
            return chain.filter(exchange);
        }
    }

    private boolean isWithinThreeMinutes(String timeStamp) {

        try {
            long receivedTimeMillis = Long.parseLong(timeStamp);

            //注意 客户端传入的时间戳是按照 毫秒 -> 秒存储的，此处需要同步 / 1000
            long currentTimeMillis = System.currentTimeMillis() / 1000; //除以 1000 获取秒数

            //计算时间差
            // 计算时间差
            Duration duration = Duration.between(
                    Instant.ofEpochMilli(receivedTimeMillis),
                    Instant.ofEpochMilli(currentTimeMillis)
            );
            return duration.toMinutes() <= 3;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Mono<Void> handleNoAuth(ServerHttpResponse response){
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return response.setComplete();
    }

    public Mono<Void> noAuthAns(ServerHttpResponse response){
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    public Mono<Void> handleInvokeError(ServerHttpResponse response){
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return response.setComplete();
    }
}