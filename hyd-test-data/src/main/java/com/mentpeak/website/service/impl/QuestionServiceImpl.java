package com.mentpeak.website.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mentpeak.website.dto.QuestionDTO;
import com.mentpeak.website.entity.*;
import com.mentpeak.website.mapper.*;
import com.mentpeak.website.service.IQuestionService;
import com.mentpeak.website.util.ObectUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * <p>
 * 企业测评题干信息表 服务实现类
 * </p>
 *
 * @author hzl
 * @since 2022-07-11
 */
@Service
@AllArgsConstructor
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {
    private final OptionMapper optionMapper;

    private final TestDimensionMapper dimensionMapper;

    private final IndexMapper indexMapper;

    private final QuestionnaireDimensionMapper questionnaireDimensionMapper;

    private final DimensionIndexQuestionMapper dimensionIndexQuestionMapper;

    @Override
    public boolean importFile(MultipartFile file, String name) throws IOException {
        InputStream inputStream = file.getInputStream();
        List<QuestionDTO> list = EasyExcel.read(inputStream)
                .head(QuestionDTO.class)
                // 设置sheet,默认读取第一个
                .sheet(name)
                // 设置标题所在行数
                .headRowNumber(1)
                .doReadSync();
        List<QuestionDTO> sleepList = list.stream().filter(sleepDTO -> ObectUtil.checkObjFieldIsNotNull(sleepDTO))
                .collect(Collectors.toList());

        System.out.println(sleepList);

        // 维度id
        AtomicReference<Long> dimensionId = new AtomicReference<>(0L);
        // 指标id
        AtomicReference<Long> indexId = new AtomicReference<>(0L);
        // 题干id
        AtomicReference<Long> questionId = new AtomicReference<>(0L);

        sleepList.forEach(sleepDTO -> {
            if (ObjectUtils.isNotEmpty(sleepDTO.getDimensionName())) {
                // 维度表
                TestDimension testDimension = new TestDimension();
                testDimension.setName(sleepDTO.getDimensionName());
                testDimension.setRemark(sleepDTO.getInstruction());
                dimensionMapper.insert(testDimension);
                // 测试问卷-维度关联表
                QuestionnaireDimension questionnaireDimension = new QuestionnaireDimension();
                questionnaireDimension.setQuestionnaireId(2L);
                questionnaireDimension.setDimensionId(testDimension.getId());
                questionnaireDimensionMapper.insert(questionnaireDimension);
                dimensionId.set(testDimension.getId());
            }
            if (ObjectUtils.isNotEmpty(sleepDTO.getTargetName())) {
                // 指标表
                Index index = new Index();
                index.setName(sleepDTO.getTargetName());
                index.setDimensionId(dimensionId.get());
                indexMapper.insert(index);
                indexId.set(index.getId());
            }
            if (ObjectUtils.isNotEmpty(sleepDTO.getTitle())) {
                // 保存题干
                Question question = new Question();
                question.setTitle(sleepDTO.getTitle());
                // 题型默认为1
                question.setQuestionType(1L);
                // 问卷id设定为1
//                question.setQuestionnaireId(2L);
                question.setSort(sleepDTO.getSort());
                baseMapper.insert(question);
                questionId.set(question.getId());
                // 保存指标题干关联表
                DimensionIndexQuestion dimensionIndexQuestion = new DimensionIndexQuestion();
                if (ObjectUtils.isNotEmpty(indexId.get())) {
                    dimensionIndexQuestion.setIndexId(indexId.get());
                }
                dimensionIndexQuestion.setQuestionId(question.getId());
                dimensionIndexQuestion.setDimensionId(dimensionId.get());
                dimensionIndexQuestionMapper.insert(dimensionIndexQuestion);
            }
            // 插入题支
            Option option = new Option();
            option.setTitle(sleepDTO.getOptionTitle());
            option.setQuestionId(questionId.get());
            option.setScore(sleepDTO.getScore());
            option.setSort(sleepDTO.getScore() + 1);
            optionMapper.insert(option);
        });
        return true;
    }

}
