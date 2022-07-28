package top.werls.novel.crawl.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import top.werls.novel.common.utils.NetUtils;
import top.werls.novel.crawl.service.CrawlService;
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

  @Override
  public List<SearchVO> getSearch(String ua, String data, int page) throws IOException {

    page = (page - 1) * 10;
    Document bing =
        Jsoup.connect(this.bing)
            .userAgent(ua)
            .data("q", data)
            .data("first", Integer.toString(page))
            .get();
    Document baidu =
        Jsoup.connect(this.baidu)
            .userAgent(ua)
            .data("word", data)
            .data("tn", "baidu")
            .data("pn", Integer.toString(page))
            .get();
    Document google =
        Jsoup.connect(this.google)
            .userAgent(ua)
            .data("q", data)
            .data("start", Integer.toString(page))
            .get();
    List<SearchVO> res = new LinkedList<>();
    // bing
    res.addAll(bingList(bing));
    // baidu
    res.addAll(baiduList(baidu));
    // google
    res.addAll(googleSearch(google));
    // duckduckgo

    return res;
  }

  /**
   * 从bing.com 搜索数据
   *
   * @param bing
   * @return
   */
  private List<SearchVO> bingList(Document bing) {
    List<SearchVO> res = new LinkedList<>();
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
              tem.setSite(NetUtils.getDomainUrl(url));
              res.add(tem);
            }
          });
    }
    return res;
  }

  private List<SearchVO> baiduList(Document baidu) {
    List<SearchVO> res = new LinkedList<>();
    Element content = baidu.body().getElementById("content_left");
    if (content != null) {
      Elements list = content.getElementsByClass("c-container");
      list.forEach(
          i -> {
            SearchVO tem = new SearchVO();
            var url = i.attr("mu");
            tem.setUrl(url);
            tem.setSite(NetUtils.getDomainUrl(url));
            var info = i.select("div > div:nth-child(1) > h3 > a ").first();
            if (info != null) {
              tem.setTitle(info.text());
              var d = info.select("em").text();
              tem.setDescription(d);
              res.add(tem);
            }
          });
    }
    return res;
  }

  private List<SearchVO> googleSearch(Document google) {
    List<SearchVO> res = new LinkedList<>();
    Element search = google.getElementById("search");

    if (search != null) {
      var list = search.getElementsByClass("jtfYYd");
      list.forEach(
          i -> {
            SearchVO temp = new SearchVO();
            var ca = i.getElementsByClass("yuRUbf").first();
            if (ca != null) {
              var url = ca.select("a").first().attr("href");
              temp.setUrl(url);
              temp.setSite(NetUtils.getDomainUrl(url));
            }
            var info = i.getElementsByClass("MUxGbd").first();

            if (info != null) {
              var name = info.selectFirst("em").text();
              temp.setTitle(name);
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