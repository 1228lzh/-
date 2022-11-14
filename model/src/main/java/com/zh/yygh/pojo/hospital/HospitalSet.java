package com.zh.yygh.pojo.hospital;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 医院设置表
 * @TableName hospital_set
 */
@TableName(value ="hospital_set")
@Data
public class HospitalSet implements Serializable {
    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 医院名称
     */
    private String hosname;

    /**
     * 医院编号
     */
    private String hoscode;

    /**
     * api基础路径
     */
    private String apiUrl;

    /**
     * 签名秘钥
     */
    private String signKey;

    /**
     * 联系人
     */
    private String contactsName;

    /**
     * 联系人手机
     */
    private String contactsPhone;

    /**
     * 状态
     * 0 锁定
     * 1 开放
     */
    private Byte status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除(1:已删除，0:未删除)
     */
    @TableLogic
    private Byte isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}