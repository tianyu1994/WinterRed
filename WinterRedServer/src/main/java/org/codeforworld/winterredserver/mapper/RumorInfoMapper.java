package org.codeforworld.winterredserver.mapper;

import org.codeforworld.winterredserver.entity.RumorInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 谣言信息表 Mapper 接口
 * </p>
 *
 * @author kfzx-menghj
 * @since 2020-07-25
 */
public interface RumorInfoMapper extends BaseMapper<RumorInfo> {

    List<RumorInfo> queryRumorInfo();

    RumorInfo queryRumorInfoById(String id);

}
