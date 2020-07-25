package org.codeforworld.winterredserver.service.impl;

import org.codeforworld.winterredserver.entity.CheckmanFieldRelation;
import org.codeforworld.winterredserver.mapper.CheckmanFieldRelationMapper;
import org.codeforworld.winterredserver.service.CheckmanFieldRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 核查人员领域关系表 服务实现类
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-25
 */
@Service
public class CheckmanFieldRelationServiceImpl extends ServiceImpl<CheckmanFieldRelationMapper, CheckmanFieldRelation> implements CheckmanFieldRelationService {

    @Resource
    private CheckmanFieldRelationMapper checkmanFieldRelationMapper;

    @Override
    public List<CheckmanFieldRelation> queryCheckManFieldRelation(CheckmanFieldRelation checkmanFieldRelation) {
        return checkmanFieldRelationMapper.queryCheckManFieldRelation(checkmanFieldRelation);
    }

}
