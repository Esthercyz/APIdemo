package com.cyz.myapicommon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cyz.myapicommon.model.entity.InterfacesInfo;
import com.cyz.myapicommon.model.vo.InterfacesInfoVO;
import org.springframework.beans.BeanUtils;

/**
* @author esther
* @description 针对表【interfaces_info(接口信息表)】的数据库操作Service
* @createDate 2025-01-20 09:24:24
*/
public interface InnerInterfacesInfoService extends IService<InterfacesInfo> {

    void validInterfacesInfo(InterfacesInfo interfacesInfo, boolean add);

    /**
     * 从数据库中查询请求接口是否存在
     * @param path
     * @param method
     * @return
     */
    InterfacesInfo getInterfaceInfo(String path, String method);

    default InterfacesInfoVO convert2Vo(InterfacesInfo interfacesInfo) {
        InterfacesInfoVO vo = new InterfacesInfoVO();
        BeanUtils.copyProperties(interfacesInfo,vo);
        return vo;
    }
}
