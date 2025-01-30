package com.yupi.springbootinit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cyz.myapicommon.model.entity.UserInterfaceInfo;


/**
* @author esther
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2025-01-25 17:48:02
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {
    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);

    boolean invokeCount(long interfaceInfoId, long userId);

    boolean canInvoke(Long InterfaceInfoId,Long userId);
}
