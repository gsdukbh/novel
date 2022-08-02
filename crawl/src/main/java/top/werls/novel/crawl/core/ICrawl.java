package top.werls.novel.crawl.core;

import top.werls.novel.crawl.vo.BookChapterVo;

import java.io.IOException;
import java.io.Serializable;

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
}
