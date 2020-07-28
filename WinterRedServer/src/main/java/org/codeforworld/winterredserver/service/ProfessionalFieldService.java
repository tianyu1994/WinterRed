package org.codeforworld.winterredserver.service;

import org.codeforworld.winterredserver.entity.ProfessionalField;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 领域表 服务类
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-25
 */
public interface ProfessionalFieldService extends IService<ProfessionalField> {
    List<ProfessionalField> queryProfessionalField(ProfessionalField professionalField);

    List<String> getAllFieldName(ProfessionalField professionalField);
}
