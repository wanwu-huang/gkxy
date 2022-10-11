package com.mentpeak.website.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 测谎量表-题支表
 * </p>
 *
 * @author hzl
 * @since 2022-05-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("hyd_polygraph_option")
public class PolygraphOption implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 题支内容
     */
    private String title;

    /**
     * 题干ID
     */
    private Long questionId;

    /**
     * 分数
     */
    private String score;

    /**
     * 命中次数
     */
    private Integer cpc;

    /**
     * 曝光量
     */
    private Long pv;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private Long updateUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 是否已删除
     */
    private Integer isDeleted;


}
