package top.werls.novel.common.entity;

import java.io.Serial;

/**
 *
 * @author Jiawei Lee
 * @version TODO
 * @date Date: 2022/8/11
 * @since on
 */
public class CrawlEncode extends BaseEntity{
    @Serial
    private static final long serialVersionUID = 2L;
    private Integer id;
    private String  url;

    private String nameSelect;
    private Integer nameIndex;
}
