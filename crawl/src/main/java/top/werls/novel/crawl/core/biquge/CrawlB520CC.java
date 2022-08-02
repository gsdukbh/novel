package top.werls.novel.crawl.core.biquge;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import top.werls.novel.crawl.core.AbstractICrawl;
import top.werls.novel.crawl.core.ICrawl;
import top.werls.novel.crawl.vo.BookChapterVo;

import java.io.IOException;

/**
 * 网站 <a href="http://www.b520.cc">解析网站</a>
 * date created 2022/8/1
 *
 * @author Jiawei Lee
 * @version TODO
 * @since on
 */
public class CrawlB520CC  extends AbstractICrawl {

    /**
     * 获取图书信息
     *
     * @return BookChapterVo 解析后的数据
     */
    @Override
    public BookChapterVo getBookInfo() throws IOException {
        Document doc = Jsoup.connect(this.url).userAgent(ua).get();

        return null;
    }
}
