package com.yupi.springbootinit.service.impl.inner;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyz.myapicommon.model.entity.UserInterfaceInfo;
import com.cyz.myapicommon.service.InnerUserInterfaceInfoService;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.mapper.UserMapper;
import com.yupi.springbootinit.service.UserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * 内部用户接口信息服务实现类
 *
 */
@DubboService
public class InnerUserInterfacesInfoServiceImpl implements InnerUserInterfaceInfoService {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;
    @Resource
    private UserMapper userMapper;

    @Override
    public boolean invokeCount(Long interfaceInfoId, Long userId) {
        //参数校验
        if(interfaceInfoId==null||userId==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        try{
            return userInterfaceInfoService.invokeCount(interfaceInfoId, userId);
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean canInvoke(Long interfaceInfoId, Long userId) {
        if (interfaceInfoId == null || userId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        try{
            return userInterfaceInfoService.canInvoke(interfaceInfoId, userId);
        }catch (Exception e){
            return false;
        }
    }
}
