package com.mentpeak.website.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 维度指标题干关联表
 * </p>
 *
 * @author hzl
 * @since 2022-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("test_dimension_index_question")
public class DimensionIndexQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 指标ID
     */
    private Long indexId;

    /**
     * 题干ID
     */
    private Long questionId;

    /**
     * 维度ID
     */
    private Long dimensionId;


}
