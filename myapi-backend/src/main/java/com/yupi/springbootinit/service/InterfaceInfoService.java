package com.yupi.springbootinit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.springbootinit.model.entity.InterfaceInfo;
import com.yupi.springbootinit.model.entity.Post;
import io.swagger.models.auth.In;

/**
* @author esther
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2025-01-19 15:38:39
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
