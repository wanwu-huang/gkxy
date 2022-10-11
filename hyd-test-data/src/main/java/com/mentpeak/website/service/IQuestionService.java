package com.mentpeak.website.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mentpeak.website.entity.Question;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 题干信息表 服务类
 * </p>
 *
 * @author hzl
 * @since 2022-07-11
 */
public interface IQuestionService extends IService<Question> {
    @Transactional(rollbackFor = Exception.class)
    boolean importFile(MultipartFile file, String name) throws IOException;

}
