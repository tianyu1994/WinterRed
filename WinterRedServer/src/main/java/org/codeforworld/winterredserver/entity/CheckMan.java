package org.codeforworld.winterredserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 核查人员表
 * </p>
 *
 * @author kfzx-ganhy
 * @since 2020-07-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CheckMan implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 机构id
     */
    @NotNull(message = "机构id不能为空")
    private String organizationId;

    /**
     * 真实姓名
     */
    @NotNull(message = "真实姓名不能为空")
    private String checkmanName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 邮箱
     */
    @NotNull(message = "邮箱不能为空")
    private String email;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 所属领域
     */
    @NotNull(message = "所属领域不能为空")
    private String belongArea;


}
