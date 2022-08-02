package top.werls.novel.crawl.core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * date created 2022/8/1
 *
 * @author Jiawei Lee
 * @version TODO
 * @since on
 */
class ICrawlBuilderTest {

  String Chrome ="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36";

  @Test
  void CrawlB520CC() throws IOException {
    Document doc = Jsoup.connect("http://www.b520.cc/52_52542/")
            .userAgent(Chrome).get();

    Element mainInfo = doc.getElementById("maininfo");
  }
}
