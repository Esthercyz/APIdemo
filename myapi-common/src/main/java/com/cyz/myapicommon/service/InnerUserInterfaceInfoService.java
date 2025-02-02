package com.cyz.myapicommon.service;
import com.cyz.myapicommon.model.entity.UserInterfaceInfo;


/**
* @author esther
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2025-01-25 17:48:02
*/
public interface InnerUserInterfaceInfoService  {

    /**
     *
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(Long interfaceInfoId, Long userId);

    /**
     * 查询接口剩余可调用次数是否大于0
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean canInvoke(Long interfaceInfoId,Long userId);
}
