package org.codeforworld.winterredserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.codeforworld.winterredserver.entity.User;
import java.util.List;

/**
 * <p>
 * 核查人员表 Mapper 接口
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-25
 */
public interface UserMapper extends BaseMapper<User> {
    List<User> queryUser(User user);
}
