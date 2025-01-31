package com.yupi.springbootinit.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyz.myapicommon.model.entity.InterfacesInfo;
import com.cyz.myapicommon.service.InnerInterfacesInfoService;
import com.yupi.springbootinit.service.InterfacesInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService
public class InnerInterfacesInfoServiceImpl implements InnerInterfacesInfoService {

    @Resource
    private InterfacesInfoService interfacesInfoService;

    @Override
    public InterfacesInfo getInterfaceInfo(String url, String methodType) {
        QueryWrapper<InterfacesInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("url", url);
        queryWrapper.eq("method", methodType);
        return interfacesInfoService.getOne(queryWrapper);
    }
}