package com.chunmi.annualconvention.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class PictureConfig implements Serializable {
    private Long id;

    private String picUrl;

    private String type;

    private Date createTime;
    
    private String typeName;

}