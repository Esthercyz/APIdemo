package com.cyz.myapicommon.v1.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IpAnaParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private String ip;

}
