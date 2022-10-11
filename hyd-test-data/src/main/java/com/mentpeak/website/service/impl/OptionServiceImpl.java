package com.mentpeak.website.service.impl;

import com.mentpeak.website.entity.Option;
import com.mentpeak.website.mapper.OptionMapper;
import com.mentpeak.website.service.IOptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 题支信息表 服务实现类
 * </p>
 *
 * @author hzl
 * @since 2022-07-11
 */
@Service
public class OptionServiceImpl extends ServiceImpl<OptionMapper, Option> implements IOptionService {

}
