package com.mentpeak.website.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mentpeak.website.entity.Conclusion;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 报告结论表 服务类
 * </p>
 *
 * @author hzl
 * @since 2022-05-10
 */
public interface IConclusionService extends IService<Conclusion> {
    @Transactional(rollbackFor = Exception.class)
    boolean importFile(MultipartFile file) throws IOException;

    @Transactional(rollbackFor = Exception.class)
    boolean importFile2(MultipartFile file) throws IOException;
}
