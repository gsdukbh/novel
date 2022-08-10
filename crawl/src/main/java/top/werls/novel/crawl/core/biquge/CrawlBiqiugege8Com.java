package top.werls.novel.crawl.core.biquge;

import top.werls.novel.common.entity.BookChapter;
import top.werls.novel.crawl.core.AbstractICrawl;
import top.werls.novel.crawl.vo.BookChapterVo;

import java.io.IOException;

/**
 * 解析网站 <a href="https://www.biqiugege8.com"> 笔趣阁</a>
 * @author Jiawei Lee
 * @version TODO
 * @date Date: 2022/8/10
 * @since on
 */
public class CrawlBiqiugege8Com extends AbstractICrawl {

    /**
     * 获取图书信息
     *
     * @return BookChapterVo 解析后的数据
     */
    @Override
    public BookChapterVo getBookInfo() throws IOException {
        return null;
    }

    /**
     * 获取章节内容
     *
     * @return BookChapter 内容信息。 {@link BookChapter}
     */
    @Override
    public BookChapter getBookChapter() throws IOException {

        return null;
    }
}

