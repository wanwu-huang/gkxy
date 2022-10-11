package com.mentpeak.website.service.impl;

import com.mentpeak.website.entity.Index;
import com.mentpeak.website.mapper.IndexMapper;
import com.mentpeak.website.service.IIndexService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 指标表 服务实现类
 * </p>
 *
 * @author hzl
 * @since 2022-07-11
 */
@Service
public class IndexServiceImpl extends ServiceImpl<IndexMapper, Index> implements IIndexService {

}
