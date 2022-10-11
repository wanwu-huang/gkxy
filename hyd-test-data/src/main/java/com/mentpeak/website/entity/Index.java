package com.mentpeak.website.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@TableName("test_index")
public class Index implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 指标名称
     */
    private String name;

    /**
     * 维度ID
     */
    private Long dimensionId;

    /**
     * 备注
     */
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
