package com.yupi.springbootinit.model.dto.userInterfaceAnalysisVo;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;


@Data
public class InterfaceAnalysisQueryRequest implements Serializable {

    /**
     * 返回前limit的记录
     */
    private Integer limit;

    @TableField(exist = false)
    private static final long serialVersionUID=1L;
}
