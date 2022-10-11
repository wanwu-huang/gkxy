package com.mentpeak.website.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 指标表
 * </p>
 *
 * @author hzl
 * @since 2022-07-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("test_dimension")
public class TestDimension implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 维度名称
     */
    private String name;

    /**
     * 备注
     */
    @TableField("`remark`")
    private String remark;

    /**
     * 是否禁用：0禁用，1 可用
     */
    private Integer status;

    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private Long updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer isDeleted;


}
