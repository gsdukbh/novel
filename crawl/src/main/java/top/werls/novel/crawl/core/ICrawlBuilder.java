package top.werls.novel.crawl.core;

import lombok.Data;

/**
 * date created 2022/8/1
 *
 * @author Jiawei Lee
 * @version TODO
 * @since on
 */

public class ICrawlBuilder {
  private static final String CHROME =
      "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36";
  private String url;

  public static ICrawlBuilder builder() {
    return new ICrawlBuilder();
  }

  public ICrawlBuilder setUrl(String url) {
      this.url= url;
      return  this;
  }

}
