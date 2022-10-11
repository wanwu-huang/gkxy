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
public class ConclusionDTO {

    @ApiModelProperty(value = "维度名称")
    @ExcelProperty(index = 1)
    private String dimensionName;

    @ApiModelProperty(value = "判定区间")
    @ExcelProperty(index = 3)
    private String scope;

    @ApiModelProperty(value = "风险结果")
    @ExcelProperty(index = 4)
    private String riskResult;

    @ApiModelProperty(value = "结论内容")
    @ExcelProperty(index = 5)
    private String explanation;
}
