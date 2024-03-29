package top.werls.novel.crawl.service;

import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.Comparator;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.tools.cache.AsynchronousFileCacheBacking.UpdateIndexCommand;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.werls.novel.common.utils.NetUtils;
import top.werls.novel.crawl.core.ICrawl;
import top.werls.novel.crawl.core.ICrawlBuilder;
import top.werls.novel.crawl.data.Rules;
import top.werls.novel.crawl.entity.BookChapter;
import top.werls.novel.crawl.service.CrawlService;
import top.werls.novel.crawl.vo.BookChapterVo;
import top.werls.novel.crawl.vo.SearchVO;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * date created 2022/7/24
 *
 * @author Jiawei Lee
 * @version TODO
 * @since on
 */
@Service
@Slf4j
public class CrawlServiceImpl implements CrawlService {

  @Cacheable(value = "search", key = "#data")
  @Override
  public List<SearchVO> getSearch(String data) throws IOException {
    if (data.isBlank()) {
      return new ArrayList<>();
    }
    List<SearchVO> res = new LinkedList<>();
    // bing
    res.addAll(bingList(data));
    // baidu
    res.addAll(baiduList(data));
    // google
//    res.addAll(googleSearch(google));
    // 360
    res.addAll(soSearch(data));

    // 搜狗
    res.addAll(sogouSearch(data));
    //  排序已经解析的站点。
    var tem = res.stream()
        .filter(i -> ICrawlBuilder.SITE_WEB.contains(NetUtils.getDomainUrl(i.getUrl())))
        .toList();
    res.removeAll(tem);
    List<SearchVO> result = new LinkedList<>(tem);
    result.addAll(0, res);
    var nilou =
        result.stream().filter(i -> !Rules.BLACK_DOMAIN.contains(NetUtils.getDomainUrl(i.getUrl())))
            .toList();

    return nilou;
  }


  /**
   * 360搜索
   *
   * @param key 搜索词
   * @return List<SearchVO>
   * @throws IOException s
   */
  private List<SearchVO> soSearch(String key) throws IOException {
    Document so =
        Jsoup.connect(this.so)
            .userAgent(chrome)
            .data("q", key)
            .data("ie", "utf-8")
            .data("src", "360sou_newhome")
            .get();
    var list = so.select(".res-list");
    List<SearchVO> res = new ArrayList<>();
    list.forEach(
        i -> {
          // url
          var info = i.select("h3 > a").first();
          if (info != null) {
            SearchVO tem = new SearchVO();
            tem.setTitle(info.text());
            var url = info.attr("data-mdurl");
            if (!url.isBlank()) {
              tem.setUrl(url);
              res.add(tem);
            }
          }
        });
    return res;

  }

  /**
   * 搜狗搜索
   *
   * @param data 搜索词
   * @return List<SearchVO> 2
   * @throws IOException s
   */
  private List<SearchVO> sogouSearch(String data) throws IOException {
    Document doc =
        Jsoup.connect(this.sogou)
            .userAgent(chrome)
            .data("query", data)
            .data("_asf", "www.sogou.com")
            .get();
    var list = doc.getElementsByClass("vrwrap");
    List<SearchVO> res = new ArrayList<>();
    list.forEach(i -> {
      var title = i.select(".vr-title").first();
      SearchVO tem = new SearchVO();
      if (title != null) {
        tem.setTitle(title.text());
      }
      var url = i.select(".r-sech").first();
      if (url != null) {
        tem.setUrl(url.attr("data-url"));
        res.add(tem);
      }
    });
    return res;
  }


  @Override
  public BookChapterVo getBookInfo(String url) throws IOException {
    ICrawl crawl = ICrawlBuilder.builder().setUrl(url).build();
    return crawl.getBookInfo();
  }

  /**
   * 获取章节内容
   *
   * @param url 章节url
   * @return BookChapter {@link  BookChapter} 章节内容
   * @throws IOException 获取网页数据失败
   */
  @Override
  public BookChapter getChapter(String url) throws IOException {
    ICrawl crawl = ICrawlBuilder.builder().setUrl(url).build();
    return crawl.getBookChapter();
  }

  /**
   * 从bing.com 搜索数据
   *
   * @param data
   * @return
   */
  private List<SearchVO> bingList(String data) throws IOException {
    Document bing =
        Jsoup.connect(this.bing)
            .userAgent(this.chrome)
            .data("q", data)
            .get();

    List<SearchVO> res = new ArrayList<>();
    // 搜索结果
    Element bingContent = bing.body().getElementById("b_content");
    if (bingContent != null) {
      var bingList = bingContent.getElementsByClass("b_algo");
      bingList.forEach(
          i -> {
            SearchVO tem = new SearchVO();
            var e = i.select("h2 > a").first();
            if (e != null) {
              var url = e.attr("href");
              var name = e.text();
              tem.setTitle(name);
              tem.setUrl(url);
              res.add(tem);
            }
          });
    }
    return res;
  }

  /**
   * 从百度搜索内容
   *
   * @param data 搜索词
   * @return List<SearchVO>
   */
  private List<SearchVO> baiduList(String data) throws IOException {
    Document baidu =
        Jsoup.connect(this.baidu)
            .userAgent(chrome)
            .data("word", data)
            .data("tn", "baidu")
            .get();
    List<SearchVO> res = new ArrayList<>();

    Elements list = baidu.getElementsByClass("result");
    list.forEach(
        i -> {
          SearchVO tem = new SearchVO();
          var url = i.attr("mu");
          tem.setUrl(url);
          var info = i.select("div").first();
          if (info != null) {
            tem.setTitle(info.text());
            var d = info.select("em").text();
            tem.setDescription(d);
            res.add(tem);
          }
        });

    return res;
  }

  /**
   * 从google搜索内容
   *
   * @param data google搜索结果
   * @return List<SearchVO>
   */
  private List<SearchVO> googleSearch(String data) throws IOException {
    Document google =
        Jsoup.connect(this.google)
            .userAgent(chrome)
            .data("q", data)
            .get();
    List<SearchVO> res = new ArrayList<>();
    Element search = google.getElementById("search");

    if (search != null) {
      var list = search.getElementsByClass("jtfYYd");
      list.forEach(
          i -> {
            SearchVO temp = new SearchVO();
            var ca = i.getElementsByClass("yuRUbf").first();
            if (ca != null) {
              var url = ca.select("a").first();
              if (url != null) {
                temp.setUrl(url.attr("href"));
              }
            }
            var info = i.getElementsByClass("MUxGbd").first();

            if (info != null) {
              var name = info.selectFirst("em");
              if (name != null) {
                temp.setTitle(name.text());
              }
              var description = info.getElementsByTag("span");
              StringBuilder descriptions = new StringBuilder();
              for (var thr : description) {
                descriptions.append(thr.text());
              }
              temp.setDescription(descriptions.toString());
            }
            res.add(temp);
          });
    }
    return res;
  }
}
