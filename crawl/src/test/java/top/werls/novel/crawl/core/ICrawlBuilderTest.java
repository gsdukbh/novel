package top.werls.novel.crawl.core;

import com.sun.jdi.VoidType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
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

  //重点
  @Test
  void core() throws IOException {
    // body > div.listmain > dl > dd:nth-child(9)
    //    Document doc =
    // Jsoup.connect("https://www.biqiugege8.com/book/4772/").userAgent(Chrome).get();
    var c = new bookname("body > div.book > div.info > h2", 0);
    var info = new a("https://www.biqiugege8.com/book/4772/", c);

    if (info.url != null) {
      Document doc = Jsoup.connect(info.url).userAgent(Chrome).get();

      doc.select("body > div.listmain > dl > dd:nth-child(2)");
      if (info.name != null) {
        var temp = doc.select(info.name.select);
        var t = temp.get(info.name.indexItem);
        if (t != null) {
          System.out.println(t.text());
        }
      }
    }
  }

  record a(String url, bookname name) {}

  record bookname(String select, Integer indexItem) {}
}
