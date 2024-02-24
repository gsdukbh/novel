package top.werls.novel.crawl.service;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import top.werls.novel.crawl.vo.SearchVO;

import java.io.IOException;
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
//    System.setProperty("http.proxyHost", proxyHost);
//    System.setProperty("http.proxyPort", proxyPort);

    // 对https也开启代理
//    System.setProperty("https.proxyHost", proxyHost);
//    System.setProperty("https.proxyPort", proxyPort);
    var setProperty = a.getSearch("圣墟");
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
        Jsoup.connect("https://www.baidu.com/s?wd=%E9%87%8D%E5%A1%91%E5%8D%83%E7%A6%A7%E5%B9%B4%E4%BB%A3&rsv_spt=1&rsv_iqid=0x9b2031590025bbe8&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_dl=ib&rsv_sug3=3&rsv_n=2")
            .userAgent(chrome)
//            .data("wd", "圣墟")
//            .data("tn", "baiduhome_pg")
            .get();
    Elements list = doc.getElementsByClass("result");
    //
    list.forEach(
        i -> {
          // url
          var url = i.select("h3 >a ").first();
          System.out.println(url);
//          System.out.println(i);

          System.out.println("-------------------------------");
        });
  }

  @Test
  void  sougou() throws IOException {
    Document doc =
        Jsoup.connect("https://www.sogou.com/web")
            .userAgent(chrome)
            .data("query", "第一序列")
            .data("_asf", "www.sogou.com")
            .get();
    var list = doc.getElementsByClass("vrwrap");
    list.forEach(i->{
      var title = i.select(".vr-title").first().text();
      var url = i.select(".r-sech").first().attr("data-url");
      System.out.println(url);
      System.out.println(title);
      System.out.println("-------------------------------");

    });
  }

  @Test
  void qihu360() throws IOException {
    Document doc =
        Jsoup.connect("https://www.so.com/s")
            .userAgent(chrome)
            .data("q", "第一序列")
            .data("ie", "utf-8")
            .data("src","360sou_newhome")
            .get();
    var list = doc.select(".res-list");
    list.forEach(
        i -> {
          // url
          var title = i.select("h3 > a").first().text();
          var url = i.select("h3 > a").first().attr("data-mdurl");
          System.out.println(url);
          System.out.println(title);
          System.out.println(i);
          System.out.println("-------------------------------");
        });
  }

  @Test
  void google() throws IOException {
    String proxyHost = "127.0.0.1";
    String proxyPort = "7890";
    System.setProperty("http.proxyHost", proxyHost);
    System.setProperty("http.proxyPort", proxyPort);

    // 对https也开启代理
    System.setProperty("https.proxyHost", proxyHost);
    System.setProperty("https.proxyPort", proxyPort);
    Document doc =
        Jsoup.connect("https://www.google.com/search")
            .userAgent(chrome)
            .data("q", "第一序列")
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
  void  rxjava (){
  Observable<String> observable = Observable.create(emitter -> {

    emitter.onNext("hello world");

  });
  observable.map(i-> {
    i=i.replace( "hello", "hi");
    return i;
  }).subscribe(System.out::println);


  }

}
