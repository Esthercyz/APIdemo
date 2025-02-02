package com.yupi.springbootinit.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyz.myapicommon.model.entity.InterfacesInfo;
import com.cyz.myapicommon.model.entity.UserInterfaceInfo;
import com.yupi.springbootinit.annotation.AuthCheck;
import com.yupi.springbootinit.common.BaseResponse;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.common.ResultUtils;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.mapper.UserInterfaceInfoMapper;
import com.yupi.springbootinit.model.dto.userInterfaceAnalysisVo.InterfaceAnalysisQueryRequest;
import com.yupi.springbootinit.model.vo.InterfacesAnalysisVO;
import com.yupi.springbootinit.model.vo.InterfacesInfoVO;
import com.yupi.springbootinit.service.InterfacesInfoService;
import com.yupi.springbootinit.service.UserInterfaceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author YuziChen
 * @version
 * @date
 * @description:
 */
@RestController
@RequestMapping("/interfacesInfo/analysis")
@Slf4j
public class InterfaceAnalysisController {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Resource
    private InterfacesInfoService interfacesInfoService;

    @PostMapping("/top/interface/invoke")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<List<InterfacesAnalysisVO>> listTopInvokeInterfaceInfo(@RequestBody InterfaceAnalysisQueryRequest request) {
        Integer limit = request.getLimit();
        if (null == limit || 0 >= limit)
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        //1.查询用户-接口信息表，得到前limit名次接口调用次数的interfaceId和userId
        List<UserInterfaceInfo> userInterfaceInfos = userInterfaceInfoService.listInterfacesAnalysis(limit);
        //k->接口id
        //v->所有id为k的UserInterfaceInfo信息
        Map<Long, List<UserInterfaceInfo>> userInterfacesInfoMap =
                userInterfaceInfos.stream().collect(Collectors.groupingBy(UserInterfaceInfo::getInterfaceInfoId));
        //2.基于接口id查询接口信息表，得到的接口详细信息
        QueryWrapper<InterfacesInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", userInterfacesInfoMap.keySet());
        List<InterfacesInfo> interfacesInfoList = interfacesInfoService.list(queryWrapper);
        if (interfacesInfoList.isEmpty()) throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        //3.将接口对象信息列表转化为UserInterfaceAnalysisVo类型集合，填充total字段的值
        List<InterfacesAnalysisVO> analysisVOList = interfacesInfoList.stream().map(
                interfacesInfo -> {
                    long id = interfacesInfo.getId();   //接口id
                    InterfacesAnalysisVO analysisVO = new InterfacesAnalysisVO();
                    BeanUtils.copyProperties(interfacesInfo, analysisVO);

                    //填充统计到的数值 -> 查询统计接口，基于接口id查询
                    analysisVO.setTotalNum(userInterfacesInfoMap.get(id).get(0).getTotalNum());
                    return analysisVO;
                }
        ).collect(Collectors.toList());
        return ResultUtils.success(analysisVOList);
    }
}