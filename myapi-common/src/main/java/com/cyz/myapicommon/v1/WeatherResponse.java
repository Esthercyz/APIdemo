package com.cyz.myapicommon.v1;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class WeatherResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private String status;
    private String count;
    private String info;
    private String infocode;
    private List<WeatherInfo> lives;
}
