package com.chunmi.annualconvention.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;


@SuppressWarnings("serial")
@Data
public class Users implements Serializable {
    /**
     *主键
     */
    private Long id;

    /**
     *工号
     */
    private String jobNum;

    /**
     *姓名
     */
    private String userName;

    /**
     * 昵称
     */
    private String userNickName;

    /**
     *头像上传地址
     */
    private String headPic;
    
    /**
     * 电话号码
     */
    private String telNum;

    /**
     *邮寄地址
     */
    private String address;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *更新时间
     */
    private Date updateTime;

    /**
     *删除时间
     */
    private Date delTime;

    /**
     *删除标记
     */
    private String delFlag;

    /**
     *备注
     */
    private String remark;
    
    
    /**
     * 奖品信息
     */
    private Prize prize;
    
    /**
     * 微信号
     */
    private String wechatNumber;
    
    private String timeStr;
    

}