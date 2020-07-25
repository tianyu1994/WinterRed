package org.codeforworld.winterredserver.mapper;

import org.codeforworld.winterredserver.entity.CheckMan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 核查人员表 Mapper 接口
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-25
 */
public interface CheckManMapper extends BaseMapper<CheckMan> {
    List<CheckMan> queryCheckMan(CheckMan checkMan);
}
