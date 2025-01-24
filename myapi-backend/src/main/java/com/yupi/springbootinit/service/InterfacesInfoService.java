package com.yupi.springbootinit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.springbootinit.model.entity.InterfacesInfo;
import com.yupi.springbootinit.model.vo.InterfacesInfoVO;

/**
* @author esther
* @description 针对表【interfaces_info(接口信息表)】的数据库操作Service
* @createDate 2025-01-20 09:24:24
*/
public interface InterfacesInfoService extends IService<InterfacesInfo> {

    void validInterfacesInfo(InterfacesInfo interfacesInfo, boolean add);
    default InterfacesInfoVO convert2Vo(InterfacesInfo interfacesInfo) {
        InterfacesInfoVO vo = new InterfacesInfoVO();
        vo.setId(interfacesInfo.getId());
        vo.setStatus(interfacesInfo.getStatus());
        vo.setDescription(interfacesInfo.getDescription());
        vo.setName(interfacesInfo.getName());
        vo.setCreateTime(interfacesInfo.getCreateTime());
        vo.setUpdateTime(interfacesInfo.getUpdateTime());
        vo.setIsDelete(interfacesInfo.getIsDelete());
        vo.setMethod(interfacesInfo.getMethod());
        vo.setRequestHeader(interfacesInfo.getRequestHeader());
        vo.setResponseHeader(interfacesInfo.getResponseHeader());
        vo.setUserId(interfacesInfo.getUserId());
        vo.setUrl(interfacesInfo.getUrl());
        return vo;
    }
}
