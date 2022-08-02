package top.werls.novel.crawl.core;

import lombok.Data;

/**
 *
 * ICrawl 工厂类，根据设置url 网站 构建对应的 ICrawl 实现类。
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
  private String ua;

  public static ICrawlBuilder builder() {
    return new ICrawlBuilder();
  }

  public ICrawlBuilder setUrl(String url) {
      this.url= url;
      return  this;
  }

  public ICrawlBuilder setUa(String userAgent) {
      this.ua = userAgent;
      return this;
  }
  public  ICrawl build(){


      return  null;
  }
}
