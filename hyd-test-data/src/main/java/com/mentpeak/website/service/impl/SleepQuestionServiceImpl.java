package com.mentpeak.website.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.mentpeak.website.dto.SleepDTO;
import com.mentpeak.website.entity.SleepOption;
import com.mentpeak.website.entity.SleepQuestion;
import com.mentpeak.website.listenter.SleepListenter;
import com.mentpeak.website.mapper.SleepOptionMapper;
import com.mentpeak.website.mapper.SleepQuestionMapper;
import com.mentpeak.website.service.ISleepQuestionService;
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
 * 睡眠量表-题干表 服务实现类
 * </p>
 *
 * @author hzl
 * @since 2022-05-09
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SleepQuestionServiceImpl extends ServiceImpl<SleepQuestionMapper, SleepQuestion> implements ISleepQuestionService {

    private final SleepQuestionMapper questionMapper;

    private final SleepOptionMapper optionMapper;

    @Override
    public boolean importSleep(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        List<SleepDTO> list = EasyExcel.read(inputStream)
                .head(SleepDTO.class)
                // 设置sheet,默认读取第一个
                .sheet("心理状态--睡眠量表题目")
                // 设置标题所在行数
                .headRowNumber(1)
                .doReadSync();
        List<SleepDTO> sleepList = list.stream().filter(sleepDTO -> ObectUtil.checkObjFieldIsNotNull(sleepDTO))
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
                SleepQuestion sleepQuestion = new SleepQuestion();
                sleepQuestion.setTitle(sleepDTO.getTitle());
                // 题型默认为1
                sleepQuestion.setQuestionType(1L);
                // 问卷id设定为1
                sleepQuestion.setQuestionnaireId(1L);
                sleepQuestion.setSort(tsort.get());
                sleepQuestion.setStatus(0);
                sleepQuestion.setIsDeleted(1);
                questionMapper.insert(sleepQuestion);
                questionId.set(sleepQuestion.getId());
                tsort.set(tsort.get() + 1);
                sort.set(0);
            }
            sort.getAndSet(sort.get() + 1);
            // 插入题支
            SleepOption option = new SleepOption();
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
