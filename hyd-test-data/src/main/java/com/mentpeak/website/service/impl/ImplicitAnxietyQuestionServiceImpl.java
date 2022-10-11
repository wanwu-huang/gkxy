package com.mentpeak.website.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mentpeak.website.dto.ImplicitDTO;
import com.mentpeak.website.entity.ImplicitAnxietyOption;
import com.mentpeak.website.entity.ImplicitAnxietyQuestion;
import com.mentpeak.website.entity.ImplicitDepressionOption;
import com.mentpeak.website.entity.ImplicitDepressionQuestion;
import com.mentpeak.website.mapper.ImplicitAnxietyOptionMapper;
import com.mentpeak.website.mapper.ImplicitAnxietyQuestionMapper;
import com.mentpeak.website.mapper.ImplicitDepressionOptionMapper;
import com.mentpeak.website.mapper.ImplicitDepressionQuestionMapper;
import com.mentpeak.website.service.IImplicitAnxietyQuestionService;
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
 * 内隐焦虑-题干表 服务实现类
 * </p>
 *
 * @author hzl
 * @since 2022-05-09
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ImplicitAnxietyQuestionServiceImpl extends ServiceImpl<ImplicitAnxietyQuestionMapper, ImplicitAnxietyQuestion> implements IImplicitAnxietyQuestionService {
    private final ImplicitAnxietyOptionMapper optionMapper;

    private final ImplicitDepressionQuestionMapper depressionQuestionMapper;

    private final ImplicitDepressionOptionMapper depressionOptionMapper;

    @Override
    public boolean importFile(MultipartFile file, Integer type) throws IOException {
        InputStream inputStream = file.getInputStream();
        List<ImplicitDTO> list = null;
        if (type == 0) {
            list = EasyExcel.read(inputStream)
                    .head(ImplicitDTO.class)
                    // 设置sheet,默认读取第一个
                    .sheet("心理状态--内隐焦虑")
                    // 设置标题所在行数
                    .headRowNumber(1)
                    .doReadSync();
        } else if (type == 1) {
            list = EasyExcel.read(inputStream)
                    .head(ImplicitDTO.class)
                    // 设置sheet,默认读取第一个
                    .sheet("心理状态--内隐抑郁")
                    // 设置标题所在行数
                    .headRowNumber(1)
                    .doReadSync();
        }
        List<ImplicitDTO> sleepList = list.stream().filter(sleepDTO -> ObectUtil.checkObjFieldIsNotNull(sleepDTO))
                .collect(Collectors.toList());

        System.out.println(sleepList);

        // 阶段id
        AtomicReference<Integer> stage = new AtomicReference<>(0);
        // 题干id
        AtomicReference<Long> questionId = new AtomicReference<>(0L);
        // 顺序sort
        AtomicReference<Integer> sort = new AtomicReference<>(0);
        // 题干顺序
        AtomicReference<Integer> tsort = new AtomicReference<>(1);
        sleepList.forEach(sleepDTO -> {
            if (ObjectUtils.isNotEmpty(sleepDTO.getStage())) {
                // 阶段
                stage.set(sleepDTO.getStage());
            }
            if (ObjectUtils.isNotEmpty(sleepDTO.getTitle())) {
                if (type == 0) {
                    // 保存题干
                    ImplicitAnxietyQuestion question = new ImplicitAnxietyQuestion();
                    question.setTitle(sleepDTO.getTitle());
                    // 题型默认为1---鼠标题2
                    question.setQuestionType(2L);
                    // 问卷id设定为1---内隐焦虑9   内隐抑郁8
                    question.setQuestionnaireId(9L);
                    question.setSort(tsort.get());
                    question.setStatus(0);
                    question.setStage(stage.get());
                    baseMapper.insert(question);
                    questionId.set(question.getId());
                    tsort.set(tsort.get() + 1);
                    sort.set(0);
                } else if (type == 1) {
                    // 保存题干
                    ImplicitDepressionQuestion question = new ImplicitDepressionQuestion();
                    question.setTitle(sleepDTO.getTitle());
                    // 题型默认为1---鼠标题2
                    question.setQuestionType(2L);
                    // 问卷id设定为1---内隐焦虑9   内隐抑郁8
                    question.setQuestionnaireId(8L);
                    question.setSort(tsort.get());
                    question.setStatus(0);
                    question.setStage(stage.get());
                    depressionQuestionMapper.insert(question);
                    questionId.set(question.getId());
                    tsort.set(tsort.get() + 1);
                    sort.set(0);
                }

            }
            if (type == 0) {
                sort.getAndSet(sort.get() + 1);
                // 插入题支
                ImplicitAnxietyOption option = new ImplicitAnxietyOption();
                option.setTitle(sleepDTO.getOptionTitle());
                option.setQuestionId(questionId.get());
                option.setScore(sleepDTO.getScore());
                option.setIsRight(sleepDTO.getScore());
                option.setSort(sort.get());
                option.setStatus(0);
                option.setIsDeleted(1);
                optionMapper.insert(option);
            } else if (type == 1) {
                sort.getAndSet(sort.get() + 1);
                // 插入题支
                ImplicitDepressionOption option = new ImplicitDepressionOption();
                option.setTitle(sleepDTO.getOptionTitle());
                option.setQuestionId(questionId.get());
                option.setScore(sleepDTO.getScore());
                option.setIsRight(sleepDTO.getScore());
                option.setSort(sort.get());
                option.setStatus(0);
                option.setIsDeleted(1);
                depressionOptionMapper.insert(option);
            }

        });
        return true;
    }
}
