package com.mentpeak.website.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 问卷维度关联表
 * </p>
 *
 * @author hzl
 * @since 2022-07-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("test_questionnaire_dimension")
public class QuestionnaireDimension implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 测试问卷ID
     */
    private Long questionnaireId;

    /**
     * 维度ID
     */
    private Long dimensionId;


}
