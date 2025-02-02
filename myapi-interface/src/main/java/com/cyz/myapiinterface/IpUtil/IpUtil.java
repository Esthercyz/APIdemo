package com.cyz.myapiinterface.IpUtil;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.lionsoul.ip2region.xdb.Searcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class IpUtil {
    private static final Logger logger = LoggerFactory.getLogger(IpUtil.class);
    private final static String localIp = "127.0.0.1";

    private static Searcher searcher = null;
    private static String filePath = null;
    /**
     *   在服务启动时加载 ip2region.db 到内存中
     *   解决打包jar后找不到 ip2region.db 的问题
     */
    static {
        try {
            InputStream ris = IpUtil.class.getResourceAsStream("/ip2region/ip2region.xdb");
            byte[] dbBinStr = FileCopyUtils.copyToByteArray(ris);
            searcher = Searcher.newWithBuffer(dbBinStr);
            //注意：不能使用文件类型，打成jar包后，会找不到文件
            logger.debug("缓存成功！！！！");
        } catch (IOException e) {
            logger.error("解析ip地址失败,无法创建搜索器: {}", e);
            throw new RuntimeException(e);
        }
    }
    public static String getIpAddr(HttpServletRequest request) {
        String ip = null;
        try {
            ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }

            if (!StringUtils.isEmpty(ip)) {
                // 多次反向代理后会有多个IP值，第一个为真实IP。
                int index = ip.indexOf(',');
                if (index != -1) {
                    return ip.substring(0, index);
                } else {
                    return ip;
                }
            }
        } catch (Exception e) {
        }
        return ip;
    }

    public static String getIpLocation(String ip) {
        // 1、创建 searcher 对象
        Searcher searcher = null;
        String dbPath = "src/main/resources/ip2region/ip2region.xdb";
        File file = null;
        try {
            Resource resource = new ClassPathResource(dbPath);
            InputStream inputStream = resource.getInputStream();
            if (filePath == null) {
                file = File.createTempFile("ip2region", ".xdb");
                FileUtils.copyInputStreamToFile(inputStream, file);
                filePath = file.getAbsolutePath();
            }
            searcher = Searcher.newWithFileOnly(filePath);
            return searcher.search(ip);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                // 3、关闭资源
                searcher.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
