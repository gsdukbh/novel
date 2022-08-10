package top.werls.novel.crawl.core.biquge;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import top.werls.novel.common.entity.BookChapter;
import top.werls.novel.crawl.core.AbstractICrawl;
import top.werls.novel.crawl.vo.BookChapterVo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 网站 <a href="http://www.b520.cc">解析网站</a>
 * date created 2022/8/1
 * @author Jiawei Lee
 * @version TODO
 * @since on
 */
public class CrawlB520CC extends AbstractICrawl {
  public CrawlB520CC() {}

  public CrawlB520CC(String url, String userAgent) {
    this.url = url;
    this.ua = userAgent;
  }

  /**
   * 获取图书信息
   *
   * @return BookChapterVo 解析后的数据
   */
  @Override
  public BookChapterVo getBookInfo() throws IOException {
    Document doc = Jsoup.connect(this.url).userAgent(ua).get();
    BookChapterVo bookChapter = new BookChapterVo();
    Element mainInfo = doc.getElementById("maininfo");
    if (mainInfo != null) {
      var name = mainInfo.select("#info > h1");
      bookChapter.setBookName(name.text());
      var author = mainInfo.select("#info > p");
      bookChapter.setAuthor(author.text());
      var description = mainInfo.select("#intro > p");
      bookChapter.setDescription(description.text());
    }
    var img = doc.select("#fmimg > img").first();
    if (img !=null){
      bookChapter.setImg(img.attr("src"));
    }
    Elements chapters = doc.select("#list > dl > dd > a");
    List<BookChapter>   chaptersList = new ArrayList<>();
    int count = 1;
    for (var item : chapters) {
      if (count > 9) {
        var url = item.attr("href");
        var chapterName = item.text();
        BookChapter chapter = new BookChapter();
        chapter.setUrl(url);
        chapter.setName(chapterName);
        chaptersList.add(chapter);
      }
      count++;
    }
    bookChapter.setChapters(chaptersList);
    return bookChapter;
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
    res.setUrl(url);
    var name = doc.select("#wrapper > div.content_read > div > div.bookname > h1").first();
    if (name !=null ){
      res.setName(name.text());
    }
    var content= doc.getElementById("content");
    if (content!=null){
      res.setLength(content.text().length());
      res.setContent(content.text().strip());
    }
    return res;
  }
}
