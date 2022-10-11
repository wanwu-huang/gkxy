package com.mentpeak.website.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hzl
 * @data 2022年05月09日17:14
 * 企业测评导入问卷
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QuestionDTO {

    @ApiModelProperty(value = "维度名称")
    @ExcelProperty(index = 0)
    private String dimensionName;

    @ApiModelProperty(value = "指标名称")
    @ExcelProperty(index = 1)
    private String targetName;

    @ApiModelProperty(value = "指导语")
    @ExcelProperty(index = 2)
    private String instruction;

    @ApiModelProperty(value = "顺序")
    @ExcelProperty(index = 3)
    private Integer sort;

    @ApiModelProperty(value = "题目内容")
    @ExcelProperty(index = 4)
    private String title;

    @ApiModelProperty(value = "题支内容")
    @ExcelProperty(index = 6)
    private String optionTitle;

    @ApiModelProperty(value = "得分")
    @ExcelProperty(index = 7)
    private Integer score;
}
