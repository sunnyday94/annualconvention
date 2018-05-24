package com.chunmi.annualconvention.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class Prize implements Serializable{
    /**
     * 主键
     */
    private Long id;

    /**
     * 奖品等级id
     */
    private Long prizeLevelId;

    /**
     * 奖品名称
     */
    private String prizeName;

    /**
     * 奖品数量
     */
    private Long prizeNum;
    
    /**
     * 奖品图片
     */
    private String prizePic;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除时间
     */
    private Date delTime;

    /**
     * 删除标记
     */
    private String delFlag;
    
    
    /**
     * 奖品等级
     */
    private PrizeLevel prizeLevel;

}