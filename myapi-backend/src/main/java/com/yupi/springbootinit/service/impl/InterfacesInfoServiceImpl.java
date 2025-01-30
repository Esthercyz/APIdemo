package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyz.myapicommon.model.entity.InterfacesInfo;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.mapper.InterfacesInfoMapper;
import com.yupi.springbootinit.service.InterfacesInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


/**
* @author esther
* @description 针对表【interfaces_info(接口信息表)】的数据库操作Service实现
* @createDate 2025-01-20 09:24:24
*/
@Service
public class InterfacesInfoServiceImpl extends ServiceImpl<InterfacesInfoMapper, InterfacesInfo>
    implements InterfacesInfoService {
    @Override
    public void validInterfacesInfo(InterfacesInfo interfacesInfo, boolean add) {
        if (interfacesInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String name = interfacesInfo.getName();
        // 创建时，所有参数必须非空
        if (add) {
            if (StringUtils.isAnyBlank(name)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        if (StringUtils.isNotBlank(name) && name.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "名称过长");
        }
    }
}




