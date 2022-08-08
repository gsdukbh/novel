package top.werls.novel.crawl.core.biquge;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import top.werls.novel.crawl.core.ICrawl;
import top.werls.novel.crawl.core.ICrawlBuilder;

import javax.xml.crypto.dsig.dom.DOMValidateContext;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CrawlB520CCTest {
  String Chrome =
      "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36";

  @Test
  void getBookInfo() throws IOException {
    Document doc = Jsoup.connect("http://www.b520.cc/52_52542/").userAgent(Chrome).get();

    Element mainInfo = doc.getElementById("maininfo");
    assert mainInfo != null;
    var name = mainInfo.select("#info > h1");
    System.out.println(name.text());
    var description = mainInfo.select("#intro > p");
    System.out.println(description.text());
    var author = mainInfo.select("#info > p");
    System.out.println(author.get(0).text());
    Elements chapters = doc.select("#list > dl > dd > a");
    var img = doc.select("#fmimg > img").first();
    assert img != null;
    System.out.println(img.attr("src"));
    int count = 1;
    for (var item : chapters) {
      if (count > 9) {
        var url = item.attr("href");
        var chapterName = item.text();
        System.out.println("url: " + url + "  name: " + chapterName);
      }
      count++;
    }
  }

  @Timeout(2)
  @Test
  void build() throws IOException {
    ICrawl iCrawl = ICrawlBuilder.builder().setUa(Chrome).setUrl("http://www.b520.cc/38_38857/").build();
    System.out.println(iCrawl.getBookInfo());
  }
}
