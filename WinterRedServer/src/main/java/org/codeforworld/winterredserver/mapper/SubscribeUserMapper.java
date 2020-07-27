package org.codeforworld.winterredserver.mapper;

import org.codeforworld.winterredserver.entity.SubscribeUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-27
 */
public interface SubscribeUserMapper extends BaseMapper<SubscribeUser> {
    List<SubscribeUser> querySubscribeUser(SubscribeUser subscribeUser);
}
