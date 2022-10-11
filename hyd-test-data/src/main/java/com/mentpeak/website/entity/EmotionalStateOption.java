package com.mentpeak.website.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 情绪状态量表-题支表
 * </p>
 *
 * @author hzl
 * @since 2022-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("hyd_emotional_state_option")
public class EmotionalStateOption implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 题干ID
     */
    private Long questionId;

    /**
     * 题支的题序
     */
    private Integer sort;

    /**
     * 题支内容
     */
    private String title;

    /**
     * 得分
     */
    private Integer score;

    /**
     * 曝光量
     */
    private Integer pv;

    /**
     * 命中次数
     */
    private Integer cpc;

    /**
     * 备注
     */
    private String remarks;

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
