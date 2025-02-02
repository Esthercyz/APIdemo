package com.cyz.myapicommon.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LanguageEnum {
    CHI("chi_sim"),
    ENG("eng");

    private final String value ;

}
