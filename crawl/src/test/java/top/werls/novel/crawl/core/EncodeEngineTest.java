package top.werls.novel.crawl.core;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

/**
 * @author Li JiaWei
 * @version TODO
 * @date 2023/2/17
 * @since on
 */

class EncodeEngineTest {

  private  String url ="";

  private String  ua ="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36";
  @Test
  void getBookInfo() throws IOException {
    Document doc = Jsoup.connect(this.url).userAgent(ua).get();
  }

  @Test
  void getBookChapter() {
  }

  @Test
  void getBookChapterByPages() {
  }
}