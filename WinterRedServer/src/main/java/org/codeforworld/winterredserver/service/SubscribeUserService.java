package org.codeforworld.winterredserver.service;

import org.codeforworld.winterredserver.entity.SubscribeUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-27
 */
public interface SubscribeUserService extends IService<SubscribeUser> {
    List<SubscribeUser> querySubscribeUser(SubscribeUser subscribeUser);
}
