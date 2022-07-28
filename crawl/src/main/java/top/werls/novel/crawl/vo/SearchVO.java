package top.werls.novel.crawl.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * date created 2022/7/26
 *
 * @author Jiawei Lee
 * @version TODO
 * @since on
 */
@Data
public class SearchVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private  String url;
    private  String title;
    private  String description;
    private boolean encoded;
    private  String site;
}
