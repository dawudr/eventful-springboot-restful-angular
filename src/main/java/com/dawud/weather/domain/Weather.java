package com.dawud.weather.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather implements Serializable {

    private static final long serialVersionUID = -7788619177798333715L;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    Date dt_txt;

    String description;

    String temp;

    public Date getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(Date dt_txt) {
        this.dt_txt = dt_txt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
