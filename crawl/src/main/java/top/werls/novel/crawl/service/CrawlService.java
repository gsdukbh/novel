package top.werls.novel.crawl.service;


import top.werls.novel.crawl.entity.BookChapter;
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
  String google = "https://www.google.com/search";

  String chrome =
      "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36";

  List<SearchVO> getSearch(String data, int page) throws IOException;

  /**
   * 解析图书信息
   * @param url url 地址
   * @return  BookChapterVo {@link  BookChapterVo}
   * @throws IOException 获取网页数据失败
   */
  BookChapterVo getBookInfo(String url) throws IOException;

  /**
   * 获取章节内容
   * @param url 章节url
   * @return BookChapter {@link  BookChapter} 章节内容
   * @throws IOException 获取网页数据失败
   */
  BookChapter getChapter(String url) throws IOException;
}
