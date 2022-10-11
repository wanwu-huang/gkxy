package com.mentpeak.website.controller;


import com.mentpeak.website.entity.SleepQuestion;
import com.mentpeak.website.service.ISleepQuestionService;
import com.mentpeak.website.util.Result;
import com.mentpeak.website.util.ResultEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 睡眠量表-题干表 前端控制器
 * </p>
 *
 * @author hzl
 * @since 2022-05-09
 */
@Api(value = "睡眠问卷接口", tags = "睡眠问卷接口")
@Controller
@RequestMapping("/sleep-question")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SleepQuestionController {

    private final ISleepQuestionService sleepQuestionService;

    @PostMapping("/import")
    @ApiOperation(value = "导入睡眠问卷")
    public Result importSleep(@ApiParam(value = "上传的文件", required = true) @RequestPart("file") MultipartFile file) {
        boolean b = false;
        try {
            b = sleepQuestionService.importSleep(file);
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
