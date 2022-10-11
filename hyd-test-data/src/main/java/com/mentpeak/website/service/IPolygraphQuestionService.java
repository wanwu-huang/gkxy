package com.mentpeak.website.service;

import com.mentpeak.website.entity.PolygraphQuestion;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 测谎量表-题干表 服务类
 * </p>
 *
 * @author hzl
 * @since 2022-05-10
 */
public interface IPolygraphQuestionService extends IService<PolygraphQuestion> {

    @Transactional(rollbackFor = Exception.class)
    boolean importFile(MultipartFile file) throws IOException;

}
