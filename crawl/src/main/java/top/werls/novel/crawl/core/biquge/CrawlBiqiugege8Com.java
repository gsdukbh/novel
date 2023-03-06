package top.werls.novel.crawl.core.biquge;


import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import top.werls.novel.common.utils.ChineseNumeralsUtils;
import top.werls.novel.common.utils.TextUtils;
import top.werls.novel.crawl.core.AbstractICrawl;
import top.werls.novel.crawl.entity.BookChapter;
import top.werls.novel.crawl.vo.BookChapterVo;

import java.io.IOException;

/**
 * 解析网站 <a href="https://www.biqiugege8.com"> 笔趣阁</a>
 *
 * @author Jiawei Lee
 * @version TODO
 * @date Date: 2022/8/10
 * @since on
 */
public class CrawlBiqiugege8Com extends AbstractICrawl {

  private final String base = "https://www.biqiugege8.com/";

  /**
   * 获取图书信息
   *
   * @return BookChapterVo 解析后的数据
   */
  @Override
  public BookChapterVo getBookInfo() throws IOException {
    BookChapterVo res = new BookChapterVo();
    Document doc = Jsoup.connect(this.url).userAgent(ua).get();
    // book name
    var n = doc.select("body > div.book > div.info > h2");
    if (n.size() > 0) {
      res.setBookName(n.get(0).text());
    }
    // author
    var author = doc.select("body > div.book > div.info > div.small > span:nth-child(1)");
    if (author.size() > 0) {
      res.setAuthor(author.get(0).text().replace("作者：", ""));
    }
    //setDescription
    var description = doc.select("body > div.book > div.info > div.intro");
    if (description.size() > 0) {
      res.setDescription(description.get(0).text());
    }
    // img
    var img = doc.select("body > div.book > div.info > div.cover > img");
    if (img.size() > 0) {
      res.setImg(img.get(0).attr("src"));
    }
    // BookChapter list
    var bookChapter = doc.select("body > div.listmain > dl > dd > a");
    List<BookChapter> chaptersList = new ArrayList<>();
    int count = 1;
    if (bookChapter.size() > 0) {
      for (var tem : bookChapter) {
        if (count > 7) {
          BookChapter chapter = new BookChapter();
          chapter.setUrl(base + tem.attr("href"));
          chapter.setName(tem.text());
          chapter.setHash(String.valueOf(chapter.getUrl().hashCode()));
          chapter.setNumber(
              ChineseNumeralsUtils.ChineseNumeralsToNum(TextUtils.chapterNumber(tem.text()))
                  .intValue());
          chaptersList.add(chapter);
        }
        count++;
      }
    }
    res.setChapters(chaptersList);
    return res;
  }

  /**
   * 获取章节内容
   *
   * @return BookChapter 内容信息。 {@link BookChapter}
   */
  @Override
  public BookChapter getBookChapter() throws IOException {
    Document doc = Jsoup.connect(this.url).userAgent(ua).get();
    BookChapter res = new BookChapter();

    res.setUrl(this.url);
    var name = doc.select("#wrapper > div.book.reader > div.content > h1");
    if (name.size() > 0) {
      res.setName(name.text());
      res.setNumber(ChineseNumeralsUtils.ChineseNumeralsToNum(TextUtils.chapterNumber(name.text()))
          .intValue());
    }
    var con = doc.select("#content");
    if (con.size() > 0) {
      res.setContent(doc.text());
    }
    return res;
  }
}

