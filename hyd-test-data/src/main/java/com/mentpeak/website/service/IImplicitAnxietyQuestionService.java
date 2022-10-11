package com.mentpeak.website.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mentpeak.website.entity.ImplicitAnxietyQuestion;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 内隐焦虑-题干表 服务类
 * </p>
 *
 * @author hzl
 * @since 2022-05-09
 */
public interface IImplicitAnxietyQuestionService extends IService<ImplicitAnxietyQuestion> {
    @Transactional(rollbackFor = Exception.class)
    boolean importFile(MultipartFile file,Integer type) throws IOException;
}
