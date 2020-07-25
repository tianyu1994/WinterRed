package org.codeforworld.winterredserver.service.impl;

import org.codeforworld.winterredserver.entity.CheckMan;
import org.codeforworld.winterredserver.mapper.CheckManMapper;
import org.codeforworld.winterredserver.service.CheckManService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class CheckManServiceImpl extends ServiceImpl<CheckManMapper, CheckMan> implements CheckManService {

    @Resource
    private CheckManMapper checkManMapper;

    @Override
    public List<CheckMan> queryCheckMan(CheckMan checkMan) {
        List<CheckMan> list = checkManMapper.queryCheckMan(checkMan);
        return list;
    }
}
