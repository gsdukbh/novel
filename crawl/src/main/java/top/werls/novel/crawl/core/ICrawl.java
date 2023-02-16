package top.werls.novel.crawl.core;


import top.werls.novel.crawl.entity.BookChapter;
import top.werls.novel.crawl.vo.BookChapterVo;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * 爬虫 网站接口 date created 2022/8/1
 *
 * @author Jiawei Lee
 * @version TODO
 * @since on
 */
public interface ICrawl extends Serializable {
  /**
   * 获取图书信息
   *
   * @return BookChapterVo 解析后的数据
   */
  BookChapterVo getBookInfo() throws IOException;

  /**
   * 获取章节内容
   *
   * @return BookChapter 内容信息。 {@link BookChapter}
   */
  BookChapter getBookChapter() throws IOException;

  /**
   * 某些网站是 分页展示 章节信息 url，但是不包括主体内容 需要分次请求解析。
   * @return
   * @throws IOException
   */
  List<BookChapter> getBookChapterByPages() throws IOException;
}
