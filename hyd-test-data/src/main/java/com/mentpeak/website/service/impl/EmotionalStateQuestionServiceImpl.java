package com.mentpeak.website.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mentpeak.website.dto.EmotionalStateDTO;
import com.mentpeak.website.entity.Dimension;
import com.mentpeak.website.entity.EmotionalStateOption;
import com.mentpeak.website.entity.EmotionalStateQuestion;
import com.mentpeak.website.entity.EmotionalStateQuestionDimension;
import com.mentpeak.website.mapper.DimensionMapper;
import com.mentpeak.website.mapper.EmotionalStateOptionMapper;
import com.mentpeak.website.mapper.EmotionalStateQuestionDimensionMapper;
import com.mentpeak.website.mapper.EmotionalStateQuestionMapper;
import com.mentpeak.website.service.IEmotionalStateQuestionService;
import com.mentpeak.website.util.ObectUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * <p>
 * 情绪状态量表-题干表 服务实现类
 * </p>
 *
 * @author hzl
 * @since 2022-05-09
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmotionalStateQuestionServiceImpl extends ServiceImpl<EmotionalStateQuestionMapper, EmotionalStateQuestion> implements IEmotionalStateQuestionService {
    private final EmotionalStateOptionMapper optionMapper;

    private final EmotionalStateQuestionDimensionMapper questionDimensionMapper;

    private final DimensionMapper dimensionMapper;

    @Override
    public boolean importFile(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        List<EmotionalStateDTO> list = EasyExcel.read(inputStream)
                .head(EmotionalStateDTO.class)
                // 设置sheet,默认读取第一个
                .sheet("心理状态--情绪量表题目")
                // 设置标题所在行数
                .headRowNumber(1)
                .doReadSync();
        List<EmotionalStateDTO> sleepList = list.stream().filter(sleepDTO -> ObectUtil.checkObjFieldIsNotNull(sleepDTO))
                .collect(Collectors.toList());

        System.out.println(sleepList);

        // 维度id
        AtomicReference<Long> dimensionId = new AtomicReference<>(0L);
        // 题干id
        AtomicReference<Long> questionId = new AtomicReference<>(0L);
        // 顺序sort
        AtomicReference<Integer> sort = new AtomicReference<>(0);
        // 题干顺序
        AtomicReference<Integer> tsort = new AtomicReference<>(1);
        // 查找维度
        List<Dimension> dimensionList = dimensionMapper.selectList(Wrappers.<Dimension>lambdaQuery().eq(Dimension::getStatus, 0));
        // 转map,key-name  value-id
        Map<String, Long> collect = dimensionList.stream().collect(Collectors.toMap(Dimension::getName, Dimension::getId, (k1, k2) -> k1));
        sleepList.forEach(sleepDTO -> {
            if (ObjectUtils.isNotEmpty(sleepDTO.getDimensionName())) {
                // 维度id
                Long dId = collect.get(sleepDTO.getDimensionName());
                dimensionId.set(dId);
            }
            if (ObjectUtils.isNotEmpty(sleepDTO.getTitle())) {
                // 保存题干
                EmotionalStateQuestion question = new EmotionalStateQuestion();
                question.setTitle(sleepDTO.getTitle());
                // 题型默认为1
                question.setQuestionType(1L);
                // 问卷id设定为1
                question.setQuestionnaireId(1L);
                question.setSort(tsort.get());
                question.setStatus(0);
                question.setIsDeleted(1);
                baseMapper.insert(question);
                questionId.set(question.getId());
                tsort.set(tsort.get() + 1);
                sort.set(0);
                // 保存维度题干关联表
                EmotionalStateQuestionDimension questionDimension = new EmotionalStateQuestionDimension();
                questionDimension.setQuestionDimensionId(dimensionId.get());
                questionDimension.setQuestionId(questionId.get());
                questionDimensionMapper.insert(questionDimension);
            }
            sort.getAndSet(sort.get() + 1);
            // 插入题支
            EmotionalStateOption option = new EmotionalStateOption();
            option.setTitle(sleepDTO.getOptionTitle());
            option.setQuestionId(questionId.get());
            option.setScore(sleepDTO.getScore());
            option.setSort(sort.get());
            option.setStatus(0);
            option.setIsDeleted(1);
            optionMapper.insert(option);
        });
        return true;
    }
}
