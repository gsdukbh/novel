package top.werls.novel.crawl;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.annotation.Resource;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import top.werls.novel.crawl.core.EncodeEngine;
import top.werls.novel.crawl.entity.CrawlEncode;
import top.werls.novel.crawl.vo.BookChapterVo;


/**
 * @author Li JiaWei
 * @version TODO
 * @date 2023/2/17
 * @since on
 */
@SpringBootTest
class EncodeEngineTest {

  private String url = "http://www.b520.cc/0_111/";

  @Resource
  private EncodeEngine encodeEngine;

  private String ua = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36";

  @Test
  void getBookInfo() throws IOException {
    Document doc = Jsoup.connect(this.url).userAgent(ua).get();
    CrawlEncode crawlEncode = new CrawlEncode();
    crawlEncode.setBookNameSelect("#info > h1");
    crawlEncode.setBookNameIndex(0);
    crawlEncode.setBookNameType("text");
    BookChapterVo res = new BookChapterVo();
    encodeEngine.getBookName(doc,res,crawlEncode);

    System.out.println(res);
  }

  @Test
  void getBookChapter() {
  }

  @Test
  void getBookChapterByPages() {
  }
}