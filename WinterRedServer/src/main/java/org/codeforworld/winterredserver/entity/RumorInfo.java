package org.codeforworld.winterredserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 谣言信息表
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RumorInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 摘要
     */
    private String abstractInfo;

    /**
     * 正文
     */
    private String body;

    /**
     * 辟谣状态
     */
    private String status;

    /**
     * 录入时间
     */
    private LocalDateTime createOn;

    /**
     * 更新时间
     */
    private LocalDateTime updateOn;

    /**
     * 核查人员id
     */
    private Integer checkManId;

    /**
     * 领域id
     */
    private Integer professionalFieldId;

    /**
     * 信息来源渠道
     */
    private String source;

    /**
     * 提问用户id
     */
    private Integer askUserId;


}
