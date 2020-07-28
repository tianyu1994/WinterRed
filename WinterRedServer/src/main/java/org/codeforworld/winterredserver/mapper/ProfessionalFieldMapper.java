package org.codeforworld.winterredserver.mapper;

import org.codeforworld.winterredserver.entity.ProfessionalField;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 领域表 Mapper 接口
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-25
 */
public interface ProfessionalFieldMapper extends BaseMapper<ProfessionalField> {
    List<ProfessionalField> queryProfessionalField(ProfessionalField professionalField);

    List<String> getAllFieldName(ProfessionalField professionalField);
}
