package com.example.millionData.entity;

import lombok.Data;

import java.util.Date;

@Data
public class AsyncTaskLog {

    private Integer id;

    private Date createtime;

    private Integer status;

    private String url;

}
