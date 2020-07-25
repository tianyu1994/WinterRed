package org.codeforworld.winterredserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.io.Serializable;

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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 机构id
     */
    private String organizationId;

    /**
     * 邮箱
     */
    @NotNull(message = "邮箱不能为空")
    private String email;


}
