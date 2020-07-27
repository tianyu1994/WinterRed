package org.codeforworld.winterredserver.service;

import org.codeforworld.winterredserver.entity.CheckmanFieldRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import org.codeforworld.winterredserver.lang.Result;

import java.util.List;

/**
 * <p>
 * 核查人员领域关系表 服务类
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-25
 */
public interface CheckmanFieldRelationService extends IService<CheckmanFieldRelation> {

    List<CheckmanFieldRelation> queryCheckManFieldRelation(CheckmanFieldRelation checkmanFieldRelation);

    Result saveOrUpdateCheckmanFieldRelation(CheckmanFieldRelation checkmanFieldRelation);

}
