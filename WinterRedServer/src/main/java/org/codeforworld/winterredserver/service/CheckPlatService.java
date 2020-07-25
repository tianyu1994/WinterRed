package org.codeforworld.winterredserver.service;

import org.codeforworld.winterredserver.entity.CheckPlat;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 核查平台表 服务类
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-25
 */
public interface CheckPlatService extends IService<CheckPlat> {
    List<CheckPlat> queryCheckPlat(CheckPlat checkPlat);
}
