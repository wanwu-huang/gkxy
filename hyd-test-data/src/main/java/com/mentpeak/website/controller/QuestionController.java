package com.mentpeak.website.controller;


import com.mentpeak.website.service.IQuestionService;
import com.mentpeak.website.util.Result;
import com.mentpeak.website.util.ResultEnum;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 题干信息表 前端控制器
 * </p>
 *
 * @author hzl
 * @since 2022-07-11
 */
@Controller
@RequestMapping("/question")
@AllArgsConstructor
public class QuestionController {

    private final IQuestionService questionService;

    @PostMapping("/import")
    @ApiOperation(value = "导入企业测评问卷")
    public Result importSleep(@ApiParam(value = "上传的文件", required = true) @RequestPart("file") MultipartFile file, String name) {
        boolean b = false;
        try {
            b = questionService.importFile(file, name);
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
