package com.cyz.myapicommon.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cyz.myapicommon.model.entity.User;
import com.cyz.myapicommon.model.vo.LoginUserVO;
import com.cyz.myapicommon.model.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户服务
 *
 */
public interface InnerUserService {

    /**
     * 数据库中查是否已经分配给用户密钥
     * @param accessKey
     * return
     */
    User getInvokeUser(String accessKey);


}
