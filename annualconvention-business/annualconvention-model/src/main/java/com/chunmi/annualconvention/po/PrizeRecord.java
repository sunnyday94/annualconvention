package com.chunmi.annualconvention.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class PrizeRecord implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     *用户id
     */
    private Long userId;

    /**
     *奖品id
     */
    private Long prizeId;

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