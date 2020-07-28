package org.codeforworld.winterredserver.mapper;

import org.codeforworld.winterredserver.entity.CheckPlat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 核查平台表 Mapper 接口
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-25
 */
public interface CheckPlatMapper extends BaseMapper<CheckPlat> {
    List<CheckPlat> queryCheckPlat(CheckPlat checkPlat);

    List<String> getAllOrganizationName(CheckPlat checkPlat);

    List<String> getAllBelongArea(CheckPlat checkPlat);
}
