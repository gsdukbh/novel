package top.werls.novel.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


import java.io.Serializable;
import java.util.Date;

/**
 * date created 2022/7/24
 *
 * @author Jiawei Lee
 * @version TODO
 * @since on
 */
@Getter
@Setter
public abstract class BaseEntity implements Serializable {
    /**
     * 创建日期
     */
    @Schema(name = "创建日期", example = "2021-08-25 22:11:11")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改日期
     */
    @Schema(name = "修改日期", example = "2021-08-25 22:11:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
