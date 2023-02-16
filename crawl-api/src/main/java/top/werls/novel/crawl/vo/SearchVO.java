package top.werls.novel.crawl.vo;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(title = "搜索结果",description = "返回搜索结果对象")
public class SearchVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Schema(description = "url")
    private  String url;
    @Schema(description = "title")
    private  String title;
    @Schema(description = "详情")
    private  String description;
    @Schema(description = "是否解析")
    private boolean encoded;
    private  String img;
}
