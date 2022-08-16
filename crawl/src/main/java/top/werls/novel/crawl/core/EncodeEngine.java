package top.werls.novel.crawl.core;

import top.werls.novel.common.entity.BookChapter;
import top.werls.novel.crawl.vo.BookChapterVo;

import java.io.IOException;
import java.util.List;

/**
 * 通过数据获取 解析方式获得数据
 * @author Jiawei Lee
 * @version TODO
 * @date Date: 2022/8/16
 * @since on
 */
public class EncodeEngine extends AbstractICrawl{
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

    /**
     * 某些网站是 分页展示 章节信息的，需要分次请求解析 默认不实现
     *
     * @return
     * @throws IOException
     */
    @Override
    public List<BookChapter> getBookChapterByPages() throws IOException {
        return super.getBookChapterByPages();
    }
}
