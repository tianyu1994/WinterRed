package org.codeforworld.winterredserver.service.impl;

import org.codeforworld.winterredserver.entity.ProfessionalField;
import org.codeforworld.winterredserver.mapper.ProfessionalFieldMapper;
import org.codeforworld.winterredserver.service.ProfessionalFieldService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 领域表 服务实现类
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-25
 */
@Service
public class ProfessionalFieldServiceImpl extends ServiceImpl<ProfessionalFieldMapper, ProfessionalField> implements ProfessionalFieldService {
    @Resource
    private ProfessionalFieldMapper professionalFieldMapper;
    @Override
    public List<ProfessionalField> queryProfessionalField(ProfessionalField professionalField) {
        return professionalFieldMapper.queryProfessionalField(professionalField);
    }
}
