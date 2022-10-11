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
public class Conclusion2DTO {

    @ApiModelProperty(value = "维度名称")
    @ExcelProperty(index = 2)
    private String dimensionName;

    @ApiModelProperty(value = "结论内容")
    @ExcelProperty(index = 4)
    private String explanation;
}
