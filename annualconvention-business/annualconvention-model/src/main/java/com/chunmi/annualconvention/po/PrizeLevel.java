package com.chunmi.annualconvention.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class PrizeLevel implements Serializable{
    /**
     * 主键
     */
    private Long id;

    /**
     * 奖品等级名称
     */
    private String prizeLevelName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 删除时间
     */
    private Date delTime;

    /**
     * 删除标记
     */
    private String delFlag;

}