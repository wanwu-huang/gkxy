package com.mentpeak.website.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.mentpeak.website.dto.FatigueDTO;
import com.mentpeak.website.dto.PolygraphDTO;
import com.mentpeak.website.entity.FatigueOption;
import com.mentpeak.website.entity.FatigueQuestion;
import com.mentpeak.website.entity.PolygraphOption;
import com.mentpeak.website.entity.PolygraphQuestion;
import com.mentpeak.website.mapper.FatigueOptionMapper;
import com.mentpeak.website.mapper.PolygraphOptionMapper;
import com.mentpeak.website.mapper.PolygraphQuestionMapper;
import com.mentpeak.website.service.IPolygraphQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mentpeak.website.util.ObectUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * <p>
 * 测谎量表-题干表 服务实现类
 * </p>
 *
 * @author hzl
 * @since 2022-05-10
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PolygraphQuestionServiceImpl extends ServiceImpl<PolygraphQuestionMapper, PolygraphQuestion> implements IPolygraphQuestionService {

    private final PolygraphOptionMapper optionMapper;

    @Override
    public boolean importFile(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        List<PolygraphDTO> list = EasyExcel.read(inputStream)
                .head(PolygraphDTO.class)
                // 设置sheet,默认读取第一个
                .sheet("心理状态--测谎题目")
                // 设置标题所在行数
                .headRowNumber(1)
                .doReadSync();
        List<PolygraphDTO> sleepList = list.stream().filter(sleepDTO -> ObectUtil.checkObjFieldIsNotNull(sleepDTO))
                .collect(Collectors.toList());

        System.out.println(sleepList);

        // 题干id
        AtomicReference<Long> questionId = new AtomicReference<>(0L);
        // 顺序sort
        AtomicReference<Integer> sort = new AtomicReference<>(0);
        // 题干顺序
        AtomicReference<Integer> tsort = new AtomicReference<>(1);
        sleepList.forEach(sleepDTO -> {
            if (ObjectUtils.isNotEmpty(sleepDTO.getTitle())) {
                // 保存题干
                PolygraphQuestion question = new PolygraphQuestion();
                question.setTitle(sleepDTO.getTitle());
                // 题型默认为1
                question.setQuestionType(1L);
                // 问卷id设定为1
//                question.setQuestionnaireId(1L);
                question.setSort(tsort.get());
                question.setStatus(0);
                question.setIsDeleted(1);
                baseMapper.insert(question);
                questionId.set(question.getId());
                tsort.set(tsort.get() + 1);
                sort.set(0);
            }
            sort.getAndSet(sort.get() + 1);
            // 插入题支
            PolygraphOption option = new PolygraphOption();
            option.setTitle(sleepDTO.getOptionTitle());
            option.setQuestionId(questionId.get());
            option.setScore(sleepDTO.getScore().toString());
            option.setSort(sort.get());
            option.setStatus(0);
            option.setIsDeleted(1);
            optionMapper.insert(option);
        });
        return true;
    }
}
