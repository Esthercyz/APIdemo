package com.yupi.springbootinit.model.vo;

import com.cyz.myapicommon.model.entity.InterfacesInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class InterfacesAnalysisVO extends InterfacesInfo{
    // 统计调用次数
    private Integer totalNum;
}
