package org.codeforworld.winterredserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 当前热点关键词
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CurrentHotKeywords {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 关键词
     */
    @NotNull(message = "关键词不能为空")
    private String keywords;
}
