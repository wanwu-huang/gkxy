package com.mentpeak.website.controller;


import com.mentpeak.website.service.IConclusionService;
import com.mentpeak.website.util.Result;
import com.mentpeak.website.util.ResultEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 报告结论表 前端控制器
 * </p>
 *
 * @author hzl
 * @since 2022-05-10
 */
@Controller
@RequestMapping("/conclusion")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "报告结论问卷接口", tags = "报告结论问卷接口")
public class ConclusionController {
    private final IConclusionService questionService;

    @PostMapping("/import")
    @ApiOperation(value = "导入报告结论问卷")
    public Result importSleep(@ApiParam(value = "上传的文件", required = true) @RequestPart("file") MultipartFile file) {
        boolean b = false;
        try {
            b = questionService.importFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (b) {
            return Result.success();
        } else {
            return Result.error(ResultEnum.IMPORT_FAIL);
        }
    }

    @PostMapping("/import2")
    @ApiOperation(value = "导入综合素质报告结论问卷")
    public Result importSleep2(@ApiParam(value = "上传的文件", required = true) @RequestPart("file") MultipartFile file) {
        boolean b = false;
        try {
            b = questionService.importFile2(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (b) {
            return Result.success();
        } else {
            return Result.error(ResultEnum.IMPORT_FAIL);
        }
    }
}
