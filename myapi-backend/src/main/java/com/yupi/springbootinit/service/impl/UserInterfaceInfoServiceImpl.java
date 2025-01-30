package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.cyz.myapicommon.model.entity.UserInterfaceInfo;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.mapper.UserInterfaceInfoMapper;
import com.yupi.springbootinit.service.UserInterfaceInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author esther
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service实现
* @createDate 2025-01-25 17:48:02
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService {
    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        //id 不能为空
        Long id = userInterfaceInfo.getId();
        if (id == null || id < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口Id不能为空!");
        }

        //调用用户的 Id 不能为空
        Long userId = userInterfaceInfo.getUserId();
        if (userId == null || userId < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户Id不能为空!");
        }

        //总共可调用次数不少于 0
        Integer totalNum = userInterfaceInfo.getTotalNum();
        Integer leftNum = userInterfaceInfo.getLeftNum();

        if (leftNum == null || leftNum < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "剩余可调用次数不少于 0");
        }

        Integer isDelete = userInterfaceInfo.getIsDelete();
        if (null == isDelete || (1 != isDelete && 0 != isDelete)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "更新删除状态不正确");
        }
        // 创建时，所有参数必须非空
        if (add) {
            if (StringUtils.isAnyBlank(totalNum.toString())) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "总共可调用次数不能为空");
            }
        }
    }

    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        // 判断
        if (interfaceInfoId <= 0 || userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //查询数据库更新
        UpdateWrapper<UserInterfaceInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("interfaceInfoId", interfaceInfoId);
        updateWrapper.eq("userId", userId);

        //updateWrapper.gt("leftNum", 0);
        updateWrapper.setSql("leftNum = leftNum - 1, totalNum = totalNum + 1");
        return this.update(updateWrapper);
    }
}




