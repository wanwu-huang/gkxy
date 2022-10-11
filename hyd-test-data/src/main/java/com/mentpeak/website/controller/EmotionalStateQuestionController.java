package com.mentpeak.website.controller;


import com.mentpeak.website.service.IEmotionalStateQuestionService;
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
 * 情绪状态量表-题干表 前端控制器
 * </p>
 *
 * @author hzl
 * @since 2022-05-09
 */
@Controller
@RequestMapping("/emotional-state-question")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "情绪状态问卷接口", tags = "情绪状态问卷接口")
public class EmotionalStateQuestionController {
    private final IEmotionalStateQuestionService questionService;

    @PostMapping("/import")
    @ApiOperation(value = "导入情绪状态量表问卷")
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
}
