package com.chunmi.annualconvention.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class Manager implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 头像上传地址
     */
    private String headPic;

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
     * 备注
     */
    private String remark;

}