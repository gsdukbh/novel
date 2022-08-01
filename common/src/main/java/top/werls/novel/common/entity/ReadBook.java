package top.werls.novel.common.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * date created 2022/8/1
 *
 * @author Jiawei Lee
 * @version TODO
 * @since on
 */
@Data()
@EqualsAndHashCode(callSuper = true)
public class ReadBook extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * user id
     */
    private  long uid;
    /**
     * 图书id
     */
    private  long bid;
    /**
     * 章节id 阅读到的章节
     */
    private  long cid;

}
