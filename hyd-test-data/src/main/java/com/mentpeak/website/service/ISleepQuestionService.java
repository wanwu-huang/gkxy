package com.mentpeak.website.service;

import com.mentpeak.website.entity.SleepQuestion;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 睡眠量表-题干表 服务类
 * </p>
 *
 * @author hzl
 * @since 2022-05-09
 */
public interface ISleepQuestionService extends IService<SleepQuestion> {

    @Transactional(rollbackFor = Exception.class)
    boolean importSleep(MultipartFile file) throws IOException;

}
