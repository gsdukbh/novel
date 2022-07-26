package top.werls.novel.crawl.service;

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
public interface CrawlService {
  String bing = "https://www.bing.com/search";
  String baidu = "https://www.baidu.com/s";
  String google= "https://www.google.com/search";

  List<SearchVO> getSearch(String ua, String data, int page) throws IOException;
}
