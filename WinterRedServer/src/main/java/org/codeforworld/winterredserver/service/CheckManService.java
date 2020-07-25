package org.codeforworld.winterredserver.service;

import org.codeforworld.winterredserver.entity.CheckMan;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 核查人员表 服务类
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-25
 */
public interface CheckManService extends IService<CheckMan> {

    List<CheckMan> queryCheckMan(CheckMan checkMan);
}
