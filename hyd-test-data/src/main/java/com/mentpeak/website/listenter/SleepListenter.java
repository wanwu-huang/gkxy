package com.mentpeak.website.listenter;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.mentpeak.website.dto.SleepDTO;
import com.mentpeak.website.entity.SleepOption;
import com.mentpeak.website.entity.SleepQuestion;
import com.mentpeak.website.mapper.SleepOptionMapper;
import com.mentpeak.website.mapper.SleepQuestionMapper;
import com.mentpeak.website.util.ObectUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author hzl
 * @data 2022年05月09日17:13
 */
@Slf4j
public class SleepListenter extends AnalysisEventListener<SleepDTO> {

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 1000;
    /**
     * 缓存的数据
     */
    private List<SleepDTO> list = new ArrayList<>(BATCH_COUNT);

    private SleepQuestionMapper questionMapper;

    private SleepOptionMapper optionMapper;

    public SleepListenter(SleepQuestionMapper questionMapper, SleepOptionMapper optionMapper) {
        this.questionMapper = questionMapper;
        this.optionMapper = optionMapper;
    }

    @Override
    public void invoke(SleepDTO sleepDTO, AnalysisContext analysisContext) {
        log.info("解析到一条数据：{}", JSON.toJSONString(sleepDTO));
        list.add(sleepDTO);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list = new ArrayList<>(BATCH_COUNT);
        }
    }

    // 处理数据，保存数据库
    private void saveData() {
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
                sleepQuestion.setId(sleepDTO.getId());
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
    }

    /**
     * 所有数据解析完成了 都会来调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }
}
