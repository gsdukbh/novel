package top.werls.novel.crawl.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.werls.novel.common.entity.BookChapter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 *
 * date created 2022/8/1
 *
 * @author Jiawei Lee
 * @version TODO
 * @since on
 */
@Data
@Schema(title = "图书详情",description = "包含图书，和章节信息")
public class BookChapterVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Schema(description = "书名")
    private  String bookName;
    @Schema(description = "作者")
    private  String author;
    private  String url;
    private String description;
    private  String img;
    /**
     * 章节
     */
    @Schema(description = "章节信息 不包含 内容",name = "章节")
    private List<BookChapter> chapters;
}
