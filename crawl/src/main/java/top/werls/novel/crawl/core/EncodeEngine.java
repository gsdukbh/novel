package top.werls.novel.crawl.core;

import org.hibernate.type.descriptor.sql.VarbinaryTypeDescriptor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import top.werls.novel.common.entity.BookChapter;
import top.werls.novel.common.entity.CrawlEncode;
import top.werls.novel.common.utils.NetUtils;
import top.werls.novel.crawl.repository.CrawlEncodeRepository;
import top.werls.novel.crawl.vo.BookChapterVo;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 通过数据获取 解析方式获得数据
 *
 * @author Jiawei Lee
 * @version TODO
 * @date Date: 2022/8/16
 * @since on
 */
@Component
public class EncodeEngine extends AbstractICrawl {
  @Resource CrawlEncodeRepository encodeRepository;
  /**
   * 获取图书信息
   *
   * @return BookChapterVo 解析后的数据
   */
  @Override
  public BookChapterVo getBookInfo() throws IOException {
    Document doc = Jsoup.connect(this.url).userAgent(ua).get();
    BookChapterVo res = new BookChapterVo();
    String site = NetUtils.getDomainUrl(url);
    CrawlEncode crawlEncode = encodeRepository.findBySite(site).orElse(null);
    if (crawlEncode == null) return null;
    getBookName(doc, res, crawlEncode);
    // 获取作者名称
    getAuthor(doc, res, crawlEncode);
    // 获取详情信息
    getDescription(doc, res, crawlEncode);
    // 章节目录 不包含内容
    // 需要二次点击
    if (crawlEncode.isTwoClick()) {
      var nextUrl = doc.select(crawlEncode.getTwoClickUrlSelect());
      if (nextUrl.size() > 0) {
        var url = nextUrl.get(crawlEncode.getTwoClickUrlSelectIndex()).attr("href");
        doc = Jsoup.connect(url).userAgent(ua).get();
      }
    }
    var chapterList = doc.select(crawlEncode.getChapterListSelect());
    List<BookChapter>   chaptersList = new ArrayList<>();
    for (var item: chapterList){
      var url = item.attr("href");
      var chapterName = item.text();
      BookChapter tem = new BookChapter();
      tem.setUrl(url);
      tem.setName(chapterName);
      chaptersList.add(tem);
    }
    res.setChapters(chaptersList);

    return null;
  }

  private static void getDescription(Document doc, BookChapterVo res, CrawlEncode crawlEncode) {
    var description = doc.select(crawlEncode.getDescriptionSelect());
    if (description.size() > 0) {
      var tem = description.get(crawlEncode.getDescriptionIndex());
      if (tem != null) {
        switch (crawlEncode.getDescriptionType()) {
          case "attr" -> {
            res.setAuthor(tem.attr(crawlEncode.getDescriptionTypeKey()));
          }
          case "text" -> {
            res.setAuthor(tem.text());
          }
          case "html" -> {
            res.setAuthor(tem.html());
          }
        }
      }
    }
  }

  private static void getAuthor(Document doc, BookChapterVo res, CrawlEncode crawlEncode) {
    var author = doc.select(crawlEncode.getAuthorSelect());
    if (author.size() > 0) {
      var tem = author.get(crawlEncode.getAuthorIndex());
      if (tem != null) {
        switch (crawlEncode.getAuthorType()) {
          case "attr" -> {
            res.setAuthor(tem.attr(crawlEncode.getAuthorTypeKey()));
          }
          case "text" -> {
            res.setAuthor(tem.text());
          }
          case "html" -> {
            res.setAuthor(tem.html());
          }
        }
      }
    }
  }

  private static void getBookName(Document doc, BookChapterVo res, CrawlEncode crawlEncode) {
    // 获取小说名称
    var bookName = doc.select(crawlEncode.getBookNameSelect());
    if (bookName.size() > 0) {
      var tem = bookName.get(crawlEncode.getBookNameIndex());
      if (tem != null) {
        switch (crawlEncode.getBookNameType()) {
          case "attr" -> {
            res.setBookName(tem.attr(crawlEncode.getBookNameTypeKey()));
          }
          case "text" -> {
            res.setBookName(tem.text());
          }
          case "html" -> {
            res.setBookName(tem.html());
          }
        }
      }
    }
  }

  /**
   * 获取章节内容
   *
   * @return BookChapter 内容信息。 {@link BookChapter}
   */
  @Override
  public BookChapter getBookChapter() throws IOException {
    return null;
  }

  /**
   * 某些网站是 分页展示 章节信息的，需要分次请求解析 默认不实现
   *
   * @return
   * @throws IOException
   */
  @Override
  public List<BookChapter> getBookChapterByPages() throws IOException {
    return super.getBookChapterByPages();
  }
}
