package top.werls.novel.crawl.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import top.werls.novel.crawl.service.CrawlService;
import top.werls.novel.crawl.vo.SearchVO;

import java.io.IOException;
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

    Document bing = Jsoup.connect(this.bing).userAgent(ua).data("q", data).data("first", "1").get();
    Document baidu =
        Jsoup.connect(this.baidu)
            .userAgent(ua)
            .data("word", data)
            .data("tn", "baidu")
            .data("pn", "0")
            .get();
    Document google =
        Jsoup.connect(this.google).userAgent(ua).data("q", data).data("start", "0").get();

    return null;
  }
}
