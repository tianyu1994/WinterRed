package org.codeforworld.winterredserver.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

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
    @NotNull(message = "标题不能为空")
    private String title;

    /**
     * 摘要
     */
    @NotNull(message = "摘要不能为空")
    private String abstractInfo;

    /**
     * 查证要点
     */
    private String checkPoint;

    /**
     * 引用的内容
     */
    private String quotedContent;


    /**
     * 辟谣状态
     */
    @NotNull(message = "辟谣状态不能为空")
    private String status;

    /**
     * 录入时间
     */
    private LocalDateTime createOn;

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
    @NotNull(message = "信息来源渠道不能为空")
    private String source;

    /**
     * 提问用户id
     */
    @NotNull(message = "提问用户id不能为空")
    private Integer askUserId;

    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateOn;

    /**
     * 核查人员名字
     */
    @TableField(exist = false)
    private String checkManName;

    /**
     * 核查人员领域名称
     */
    @TableField(exist = false)
    private String professionalFieldName;

    /**
     * 核查人员所属机构名称
     */
    @TableField(exist = false)
    private String organizationName;

    /**
     * 关键字查询
     */
    @TableField(exist = false)
    private String keyWord;
}
