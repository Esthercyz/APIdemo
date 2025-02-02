package com.yupi.springbootinit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyz.myapicommon.model.entity.UserInterfaceInfo;

import java.util.List;


/**
* @author esther
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Mapper
* @createDate 2025-01-25 17:48:02
* @Entity generator.domain.UserInterfaceInfo
*/
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {
    //查询前limit个调用次数最多的接口
    List<UserInterfaceInfo> listTopInvokeInterfaceInfo(Integer limit);
}




