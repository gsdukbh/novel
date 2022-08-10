package top.werls.novel.common.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

/**
 * date created 2022/8/1
 *
 * @author Jiawei Lee
 * @version TODO
 * @since on
 */
@Getter
@Setter
@ToString
public class ReadBook extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * user id
     */
    private  Integer uid;
    /**
     * 图书id
     */
    private  Integer bid;
    /**
     * 章节id 阅读到的章节
     */
    private  Integer cid;

}
