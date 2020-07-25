package org.codeforworld.winterredserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.codeforworld.winterredserver.entity.User;
import org.codeforworld.winterredserver.mapper.UserMapper;
import org.codeforworld.winterredserver.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 核查人员表 服务实现类
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> queryUser(User user) {
        List<User> list = userMapper.queryUser(user);
        return list;
    }
}
