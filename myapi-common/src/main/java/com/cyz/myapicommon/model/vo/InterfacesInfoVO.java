package com.cyz.myapicommon.model.vo;

import com.cyz.myapicommon.model.entity.InterfacesInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 接口信息封装视图
 *
 * @TableName product
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InterfacesInfoVO extends InterfacesInfo {

    /**
     * 调用次数
     */
    private Integer totalNum;

    private static final long serialVersionUID = 1L;
}