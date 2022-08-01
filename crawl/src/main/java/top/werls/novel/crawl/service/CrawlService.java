package top.werls.novel.crawl.service;

import top.werls.novel.crawl.vo.BookChapterVo;
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

  String chrome="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36";

  List<SearchVO> getSearch( String data, int page) throws IOException;

  BookChapterVo getBookInfo( String bookName,String url ) throws IOException;
}
