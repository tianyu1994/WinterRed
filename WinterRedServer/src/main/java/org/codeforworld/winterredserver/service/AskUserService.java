package org.codeforworld.winterredserver.service;

import org.codeforworld.winterredserver.entity.AskUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.codeforworld.winterredserver.lang.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-27
 */
public interface AskUserService extends IService<AskUser> {
    List<AskUser> queryAskUser(AskUser askUser);

    Result saveOrUpdateAskUser(AskUser askUser);

    Integer getAskUserIdByEmail(String email);
}
