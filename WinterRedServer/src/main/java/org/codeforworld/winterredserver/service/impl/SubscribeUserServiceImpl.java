package org.codeforworld.winterredserver.service.impl;

import org.codeforworld.winterredserver.entity.SubscribeUser;
import org.codeforworld.winterredserver.entity.SubscribeUser;
import org.codeforworld.winterredserver.mapper.SubscribeUserMapper;
import org.codeforworld.winterredserver.mapper.SubscribeUserMapper;
import org.codeforworld.winterredserver.service.SubscribeUserService;
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
public class SubscribeUserServiceImpl extends ServiceImpl<SubscribeUserMapper, SubscribeUser> implements SubscribeUserService {
    @Resource
    private SubscribeUserMapper subscribeUserMapper;

    @Override
    public List<SubscribeUser> querySubscribeUser(SubscribeUser subscribeUser) {
        List<SubscribeUser> list = subscribeUserMapper.querySubscribeUser(subscribeUser);
        return list;
    }
}
