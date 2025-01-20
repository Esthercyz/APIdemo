package com.yupi.springbootinit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.springbootinit.model.entity.InterfacesInfo;

/**
* @author esther
* @description 针对表【interfaces_info(接口信息表)】的数据库操作Service
* @createDate 2025-01-20 09:24:24
*/
public interface InterfacesInfoService extends IService<InterfacesInfo> {

    void validInterfacesInfo(InterfacesInfo interfacesInfo, boolean add);
}
