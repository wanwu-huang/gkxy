package com.mentpeak.website.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 报告结论表
 * </p>
 *
 * @author hzl
 * @since 2022-05-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("hyd_conclusion")
public class Conclusion implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 结论分类
     */
    private Long conclusionType;

    /**
     * 名称
     */
    private String name;

    /**
     * 维度ID
     */
    private Long dimensionId;

    /**
     * 风险结果
     */
    private String riskResult;

    /**
     * 区间
     */
    private String scope;

    /**
     * 结论内容
     */
    private String explanation;

    /**
     * 解析
     */
    private String analysis;

    /**
     * 建议
     */
    private String suggest;

    /**
     * 是否禁用
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
