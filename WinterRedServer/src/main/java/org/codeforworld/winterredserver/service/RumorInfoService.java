package org.codeforworld.winterredserver.service;

import org.codeforworld.winterredserver.entity.RumorInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.codeforworld.winterredserver.lang.Result;

import java.util.List;

/**
 * <p>
 * 谣言信息表 服务类
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-25
 */
public interface RumorInfoService extends IService<RumorInfo> {

    List<RumorInfo> queryRumorInfo(RumorInfo humorInfo);

    Integer insert(RumorInfo rumorInfo);

    Result modifyStatus(RumorInfo rumorInfo);

    RumorInfo queryRumorInfoById(String id);
}
