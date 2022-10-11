package com.mentpeak.website.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hzl
 * @data 2022年05月09日17:14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ImplicitDTO {

    @ApiModelProperty(value = "阶段")
    @ExcelProperty(index = 1)
    private Integer stage;

    @ApiModelProperty(value = "题目内容")
    @ExcelProperty(index = 2)
    private String title;

    @ApiModelProperty(value = "题支内容")
    @ExcelProperty(index = 4)
    private String optionTitle;

    @ApiModelProperty(value = "得分")
    @ExcelProperty(index = 5)
    private Integer score;
}
