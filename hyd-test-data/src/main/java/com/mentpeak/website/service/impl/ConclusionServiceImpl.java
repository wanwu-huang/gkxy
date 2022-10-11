package com.mentpeak.website.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mentpeak.website.dto.Conclusion2DTO;
import com.mentpeak.website.dto.ConclusionDTO;
import com.mentpeak.website.entity.Conclusion;
import com.mentpeak.website.entity.Dimension;
import com.mentpeak.website.mapper.ConclusionMapper;
import com.mentpeak.website.mapper.DimensionMapper;
import com.mentpeak.website.service.IConclusionService;
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
 * 报告结论表 服务实现类
 * </p>
 *
 * @author hzl
 * @since 2022-05-10
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ConclusionServiceImpl extends ServiceImpl<ConclusionMapper, Conclusion> implements IConclusionService {

    private final DimensionMapper dimensionMapper;

    @Override
    public boolean importFile(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        List<ConclusionDTO> list = EasyExcel.read(inputStream)
                .head(ConclusionDTO.class)
                // 设置sheet,默认读取第一个
                .sheet("心理状态分系统计分算法&测评报告")
                // 设置标题所在行数
                .headRowNumber(2)
                .doReadSync();
        List<ConclusionDTO> sleepList = list.stream().filter(sleepDTO -> ObectUtil.checkObjFieldIsNotNull(sleepDTO))
                .collect(Collectors.toList());

        System.out.println(sleepList);

        // 维度id
        AtomicReference<Long> dimensionId = new AtomicReference<>(0L);
        // 维度名称
        AtomicReference<String> name = new AtomicReference<>("");
        // 查找维度
        List<Dimension> dimensionList = dimensionMapper.selectList(Wrappers.<Dimension>lambdaQuery().eq(Dimension::getStatus, 0));
        // 转map,key-name  value-id
        Map<String, Long> collect = dimensionList.stream().collect(Collectors.toMap(Dimension::getName, Dimension::getId, (k1, k2) -> k1));
        sleepList.forEach(sleepDTO -> {
            if (ObjectUtils.isNotEmpty(sleepDTO.getDimensionName())) {
                // 维度id
                Long dId = collect.get(sleepDTO.getDimensionName());
                dimensionId.set(dId);
                name.set(sleepDTO.getDimensionName());
            }
            Conclusion conclusion = new Conclusion();
            conclusion.setScope(sleepDTO.getScope());
            conclusion.setRiskResult(sleepDTO.getRiskResult());
            conclusion.setExplanation(sleepDTO.getExplanation());
            conclusion.setDimensionId(dimensionId.get());
            conclusion.setName(name.get());
            baseMapper.insert(conclusion);
        });
        return true;
    }

    @Override
    public boolean importFile2(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        List<Conclusion2DTO> list = EasyExcel.read(inputStream)
                .head(Conclusion2DTO.class)
                // 设置sheet,默认读取第一个
                .sheet("综合素质--应战能力+意志品质计分&测评")
                // 设置标题所在行数
                .headRowNumber(1)
                .doReadSync();
        List<Conclusion2DTO> sleepList = list.stream().filter(sleepDTO -> ObectUtil.checkObjFieldIsNotNull(sleepDTO))
                .collect(Collectors.toList());

        System.out.println(sleepList);

        // 维度id
        AtomicReference<Long> dimensionId = new AtomicReference<>(0L);
        // 维度名称
        AtomicReference<String> name = new AtomicReference<>("");
        // 查找维度
        List<Dimension> dimensionList = dimensionMapper.selectList(Wrappers.<Dimension>lambdaQuery().eq(Dimension::getStatus, 0));
        // 转map,key-name  value-id
        Map<String, Long> collect = dimensionList.stream().collect(Collectors.toMap(Dimension::getName, Dimension::getId, (k1, k2) -> k1));
        sleepList.forEach(sleepDTO -> {
            if (ObjectUtils.isNotEmpty(sleepDTO.getDimensionName())) {
                // 维度id
                Long dId = collect.get(sleepDTO.getDimensionName());
                dimensionId.set(dId);
                name.set(sleepDTO.getDimensionName());
            }
            Conclusion conclusion = new Conclusion();
            conclusion.setExplanation(sleepDTO.getExplanation());
            conclusion.setDimensionId(dimensionId.get());
            conclusion.setName(name.get());
            baseMapper.insert(conclusion);
        });
        return true;
    }
}
