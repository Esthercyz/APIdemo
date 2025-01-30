package com.cyz.myapicommon.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class IdRequest implements Serializable {
    private Long id;
    private static final long serialVersionID=1L;
}
