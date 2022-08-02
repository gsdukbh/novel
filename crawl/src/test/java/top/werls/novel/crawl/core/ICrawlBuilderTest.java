package top.werls.novel.crawl.core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import top.werls.novel.common.utils.NetUtils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * date created 2022/8/1
 *
 * @author Jiawei Lee
 * @version TODO
 * @since on
 */
class ICrawlBuilderTest {

  String Chrome =
      "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36";

  @Test
  void CrawlB520CC() throws IOException {
    Document doc = Jsoup.connect("http://www.b520.cc/52_52542/").userAgent(Chrome).get();

    Element mainInfo = doc.getElementById("maininfo");
  }

  @Test
  void test() {
    String pattern = "(http(s)?://)(([\\w-]+\\.)+\\w+(:\\d{1,5})?)";
    Pattern r = Pattern.compile(pattern);
    Matcher m = r.matcher("https://www.b520.cc/52_52542/");
    if (m.find()) {
      var a = m.group();
      System.out.println(m.group(0));
      System.out.println(m.group(1));
      System.out.println(m.group(2));
      System.out.println(m.group(3));
      System.out.println(a);
    }


  }
}
