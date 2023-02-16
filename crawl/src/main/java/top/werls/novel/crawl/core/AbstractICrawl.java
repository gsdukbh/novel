package top.werls.novel.crawl.core;


import top.werls.novel.crawl.entity.BookChapter;

import java.io.IOException;
import java.io.Serial;
import java.util.List;

/**
 * 抽象类
 * date created 2022/8/1
 *
 * @author Jiawei Lee
 * @version TODO
 * @since on
 */
public abstract class AbstractICrawl implements ICrawl {
    @Serial
    private static final long serialVersionUID = 3180820087507254L;

    /**
     * 网址
     */
    protected  String url;

    /**
     * User-Agent
     */
    protected  String ua;

  /**
   * 某些网站是 分页展示 章节信息的，需要分次请求解析 默认不实现
   * @return
   * @throws IOException
   */
  @Override
  public List<BookChapter> getBookChapterByPages() throws IOException {
        return null;
    }
}
