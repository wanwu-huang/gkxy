package com.mentpeak.website.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mentpeak.website.entity.EmotionalStateQuestion;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 情绪状态量表-题干表 服务类
 * </p>
 *
 * @author hzl
 * @since 2022-05-09
 */
public interface IEmotionalStateQuestionService extends IService<EmotionalStateQuestion> {
    @Transactional(rollbackFor = Exception.class)
    boolean importFile(MultipartFile file) throws IOException;
}
