package top.werls.novel.crawl.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.aspectj.weaver.ast.Literal;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import top.werls.novel.crawl.service.impl.CrawlServiceImpl;
import top.werls.novel.crawl.vo.SearchVO;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * date created 2022/7/27
 *
 * @author Jiawei Lee
 * @version TODO
 * @since on
 */
class CrawlServiceTest {
  String chrome =
      "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36";
  String iphone12 =
      "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1";

  @Test
  void getSearch() throws IOException {
    CrawlService a = new CrawlServiceImpl();
    String proxyHost = "127.0.0.1";
    String proxyPort = "10809";
    System.setProperty("http.proxyHost", proxyHost);
    System.setProperty("http.proxyPort", proxyPort);

    // 对https也开启代理
    System.setProperty("https.proxyHost", proxyHost);
    System.setProperty("https.proxyPort", proxyPort);
    var setProperty = a.getSearch("圣墟", 1);
    System.out.println(setProperty);
  }

  @Test()
  @Timeout(value = 2, unit = TimeUnit.SECONDS)
  void contextLoads() throws IOException {
    Document doc =
        Jsoup.connect("https://www.bing.com/search")
            .userAgent(chrome)
            .data("q", "圣墟小说")
            .data("first", "0") // min 1
            .get();
    // 搜索结果
    Element content = doc.body().getElementById("b_content");
    //    System.out.println(content); #b_results > li:nth-child(3) > h2 > a

    var list = content.getElementsByClass("b_algo");
    List<SearchVO> res = new ArrayList<>();
    list.forEach(
        i -> {
          // url
          var url = i.select("h2 > a").first().attr("href");
          SearchVO tem = new SearchVO();
          tem.setUrl(url);
          //                    System.out.println(url);
          // name
          var name = i.select("h2 > a").first().text();
          tem.setTitle(name);
          tem.setEncoded(url.contains("baidu"));
          res.add(tem);
          //                    System.out.println(i.select("h2 > a").first().text());
        });
    System.out.println(res);
  }

  @Test()
  void baidu() throws IOException {
    Document doc =
        Jsoup.connect("https://www.baidu.com/s")
            .userAgent(iphone12)
            .data("word", "圣墟")
            .data("tn", "baidu")
            .data("pn", "1") // min 0
            .get();
    Element content = doc.body().getElementById("content_left");
    Elements list = content.getElementsByClass("c-container");
    //
    list.forEach(
        i -> {
          // url
          var a = i.attr("mu");
          System.out.println(a);
          // name #\31  > div > div:nth-child(1) > h3 > a
          var name = i.select("div > div:nth-child(1) > h3 > a ").first();
          if (name != null) {
            System.out.println(name.text());
            var site = name.select("em");
            System.out.println(site.text());
          }
        });
  }

  @Test
  void google() throws IOException {
    String proxyHost = "127.0.0.1";
    String proxyPort = "10809";
    System.setProperty("http.proxyHost", proxyHost);
    System.setProperty("http.proxyPort", proxyPort);

    // 对https也开启代理
    System.setProperty("https.proxyHost", proxyHost);
    System.setProperty("https.proxyPort", proxyPort);
    Document doc =
        Jsoup.connect("https://www.google.com/search")
            .userAgent(chrome)
            .data("q", "第一序列")
            .data("start", "0") // min 0
            .get();
    Element search = doc.getElementById("search");
    var list = search.getElementsByClass("jtfYYd");
    list.forEach(
        i -> {
          // url
          var ca = i.getElementsByClass("yuRUbf").first();
          var url = ca.select("a").first().attr("href");
          var tem = i.getElementsByClass("MUxGbd").first();
          // name
          var name = tem.selectFirst("em").text();
          System.out.println(url);
          System.out.println(name);
          var description = tem.getElementsByTag("span");

          StringBuilder tems = new StringBuilder();
          for (var thr : description) {
            tems.append(thr.text());
          }
          System.out.println(tems);
          System.out.println("-------------------------------");
        });
  }



  @Test
  void duckduckgo() {
    // https://duckduckgo.com/?q=

  }

  @Test
  void so() throws IOException {
    // https://www.so.com/s
    Document so =
        Jsoup.connect("https://www.so.com/s")
            .userAgent(chrome)
            .data("q", "圣墟")
            .data("pn", "1")
            .data("src", "home-sug-store")
            .get();
    Element main = so.getElementById("main");
    if (main != null) {
      var resList = main.getElementsByClass("res-list");
      resList.forEach(
          item -> {
            // url #main > ul > li:nth-child(1) > h3 > a
            var urlElement = item.select("h3 > a").first();
            if (urlElement != null) {
              var url = urlElement.attr("data-mdurl");
              if (!url.contains("360")) {
                System.out.println(url);
              }
            }
          });
    }
  }

  // 获取域名正则 http(s)?://(([\w-]+\.)+\w+(:\d{1,5})?)


}
