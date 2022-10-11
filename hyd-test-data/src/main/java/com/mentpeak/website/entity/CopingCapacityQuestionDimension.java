package com.mentpeak.website.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 应战能力-维度题干信息关联表
 * </p>
 *
 * @author hzl
 * @since 2022-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("hyd_coping_capacity_question_dimension")
public class CopingCapacityQuestionDimension implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 题目ID
     */
    private Long questionId;

    /**
     * 题目维度ID
     */
    private Long questionDimensionId;


}
