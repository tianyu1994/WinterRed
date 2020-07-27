package org.codeforworld.winterredserver.service.impl;

import org.codeforworld.winterredserver.entity.AskUser;
import org.codeforworld.winterredserver.mapper.AskUserMapper;
import org.codeforworld.winterredserver.service.AskUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class AskUserServiceImpl extends ServiceImpl<AskUserMapper, AskUser> implements AskUserService {
    @Resource
    private AskUserMapper askUserMapper;

    @Override
    public List<AskUser> queryAskUser(AskUser askUser) {
        List<AskUser> list = askUserMapper.queryAskUser(askUser);
        return list;
    }
}
