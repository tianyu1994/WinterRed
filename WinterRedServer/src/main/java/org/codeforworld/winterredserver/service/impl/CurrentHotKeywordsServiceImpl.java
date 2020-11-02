package org.codeforworld.winterredserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.codeforworld.winterredserver.entity.AskUser;
import org.codeforworld.winterredserver.entity.CurrentHotKeywords;
import org.codeforworld.winterredserver.lang.Result;
import org.codeforworld.winterredserver.mapper.AskUserMapper;
import org.codeforworld.winterredserver.mapper.CurrentHotKeywordsMapper;
import org.codeforworld.winterredserver.service.AskUserService;
import org.codeforworld.winterredserver.service.CurrentHotKeywordsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-27
 */
@Service
public class CurrentHotKeywordsServiceImpl extends ServiceImpl<CurrentHotKeywordsMapper, CurrentHotKeywords> implements CurrentHotKeywordsService {
    @Resource
    private CurrentHotKeywordsMapper currentHotKeywordsMapper;

}
