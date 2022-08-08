package top.werls.novel.crawl.core;

import top.werls.novel.common.entity.BookChapter;
import top.werls.novel.crawl.vo.BookChapterVo;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * 爬虫 网站接口
 * date created 2022/8/1
 *
 * @author Jiawei Lee
 * @version TODO
 * @since on
 */
public interface ICrawl extends Serializable {
    /**
     * 获取图书信息
     * @return BookChapterVo 解析后的数据
     */
     BookChapterVo getBookInfo() throws IOException;

    /**
     * 获取章节内容
     * @return BookChapter 内容信息。 {@link BookChapter}
     */
    BookChapter getBookChapter() throws IOException;
}
