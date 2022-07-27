package top.werls.novel.crawl.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
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
    // 搜索结果
    res.addAll(bingList(bing));
    res.addAll(baiduList(baidu));

    return null;
  }

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
              tem.setName(name);
              tem.setUrl(url);
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
            var info = i.select("div > div:nth-child(1) > h3 > a ").first();
            if (info != null) {
              tem.setName(info.text());
              var d = info.select("em").text();
              tem.setDescription(d);
              res.add(tem);
            }
          });
    }
    return res;
  }
}
