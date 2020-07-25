package org.codeforworld.winterredserver.service;

import org.codeforworld.winterredserver.entity.UserFieldRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import org.codeforworld.winterredserver.queryParam.QueryParamVo;

import java.util.List;

/**
 * <p>
 * 订阅用户领域关系表 服务类
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-25
 */
public interface UserFieldRelationService extends IService<UserFieldRelation> {
    List<UserFieldRelation> queryUserFieldRelation(UserFieldRelation userFieldRelation);
}
