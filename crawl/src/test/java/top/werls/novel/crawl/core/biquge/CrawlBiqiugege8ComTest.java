package top.werls.novel.crawl.core.biquge;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jiawei Lee
 * @version TODO
 * @date Date: 2022/8/10
 * @since on
 */
class CrawlBiqiugege8ComTest {
  String Chrome =
      "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36";

  @Test
  void getBookInfo() {}

  @Test
  void getBookChapter() throws IOException {
    Document doc = Jsoup.connect("https://www.biqiugege8.com/book/4772/").userAgent(Chrome).get();

    var bookname = doc.select("body > div.book > div.info > h2").first();
    if (bookname != null) {
      System.out.println(bookname.text());
    }
  }
}
