package com.cyz.myapicommon.v1;

import lombok.Data;

import java.io.Serializable;


@Data
public class WeatherInfo implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 省份。
     */
    private String province;

    /**
     * 城市。
     */
    private String city;

    /**
     * 行政区划代码。
     */
    private String adcode;

    /**
     * 天气状况。
     */
    private String weather;

    /**
     * 温度，字符串形式。
     */
    private String temperature;

    /**
     * 风向。
     */
    private String winddirection;

    /**
     * 风力。
     */
    private String windpower;

    /**
     * 相对湿度。
     */
    private String humidity;

    /**
     * 数据发布时间。
     */
    private String reporttime;

    /**
     * 温度，浮点数形式。
     */
    private Float temperature_float;

    /**
     * 相对湿度，浮点数形式。
     */
    private Float humidity_float;

}

