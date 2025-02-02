package com.cyz.myapicommon.v1.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author adorabled4
 * @className FileSuffixParam
 * @date : 2023/12/29/ 00:30
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileSuffixParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private String suffix;

}
