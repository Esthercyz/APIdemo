package com.cyz.myapicommon.model.bo;

import lombok.Data;

@Data
public class UserInterfaceInfo {
    private long interfaceId;
    private long userId;

    public UserInterfaceInfo(Long id, Long userId) {
        this.interfaceId=id;
        this.userId=userId;
    }
}
