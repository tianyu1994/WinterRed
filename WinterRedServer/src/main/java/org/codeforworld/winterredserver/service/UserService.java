package org.codeforworld.winterredserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.codeforworld.winterredserver.entity.User;

import java.util.List;

/**
 * <p>
 * 核查人员表 服务类
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-25
 */
public interface UserService extends IService<User> {

    List<User> queryUser(User user);
}
