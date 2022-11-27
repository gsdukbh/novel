package top.werls.novel.crawl.core;

import jakarta.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import top.werls.novel.common.entity.BookChapter;
import top.werls.novel.common.entity.CrawlEncode;
import top.werls.novel.common.utils.ChineseNumeralsUtils;
import top.werls.novel.common.utils.NetUtils;
import top.werls.novel.common.utils.TextUtils;
import top.werls.novel.crawl.repository.CrawlEncodeRepository;
import top.werls.novel.crawl.vo.BookChapterVo;


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
  @Resource
  CrawlEncodeRepository encodeRepository;
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
    //获取图片
    getImages(doc, res, crawlEncode);
    // 获取章节列表
    getChapterList(doc, res, crawlEncode);
    return res;
  }

  private  void getImages(Document doc, BookChapterVo res, CrawlEncode crawlEncode) {
    // 图像
    var img = doc.select(crawlEncode.getBookImgSelect());
    if (img.size()>0){
      var element = img.get(crawlEncode.getBookImgIndex());
      if (element!=null){
        res.setImg( element.attr("href"));
      }
    }
  }

  private  void getChapterList(Document doc, BookChapterVo res, CrawlEncode crawlEncode) throws IOException {
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
  }

  private  void getDescription(Document doc, BookChapterVo res, CrawlEncode crawlEncode) {
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

  private  void getAuthor(Document doc, BookChapterVo res, CrawlEncode crawlEncode) {
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

  private  void getBookName(Document doc, BookChapterVo res, CrawlEncode crawlEncode) {
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
    Document doc = Jsoup.connect(this.url).userAgent(ua).get();
    String site = NetUtils.getDomainUrl(url);
    BookChapter chapter =new BookChapter();
    CrawlEncode crawlEncode = encodeRepository.findBySite(site).orElse(null);
    if (crawlEncode == null) return null;
    var content = doc.select(crawlEncode.getChapterContentSelect());
    if (content.size()>0){
      var element = content.get(crawlEncode.getChapterContentIndex());
      if (element !=null){
        switch (crawlEncode.getChapterContentType()){
          case "attr":{
            chapter.setContent(element.attr(crawlEncode.getChapterContentTypeKey()));
          }
          case "text":{
            chapter.setContent(element.text());
          }
          case "html":{
            chapter.setContent(element.html());
          }
        }
      }
    }
    var name = doc.select(crawlEncode.getChapterNameSelect());
    if (name.size()> 0){
      var element =name.get(crawlEncode.getChapterNameIndex());
      if (element!=null){
        switch (crawlEncode.getChapterNameType()){
          case "attr":{
            chapter.setName(element.attr(crawlEncode.getChapterNameTypeKey()));
          }
          case "text":{
            chapter.setName(element.text());
          }
          case "html":{
            chapter.setName(element.html());
          }
        }
      }
    }
    var tem = TextUtils.chapterNumber(chapter.getName());
    chapter.setNumber(ChineseNumeralsUtils.ChineseNumeralsToNum(tem).intValue());
    chapter.setUrl(this.url);
    chapter.setHash(String.valueOf(url.hashCode()));
    chapter.setLength(chapter.getContent().length());
    return chapter;
  }

  /**
   * 某些网站是 分页展示 章节信息的，需要分次请求解析 默认不实现
   * @return
   * @throws IOException
   */
  @Override
  public List<BookChapter> getBookChapterByPages() throws IOException {
    Document doc = Jsoup.connect(url).userAgent(ua).get();
    String site = NetUtils.getDomainUrl(url);
    BookChapter chapter =new BookChapter();
    CrawlEncode crawlEncode = encodeRepository.findBySite(site).orElse(null);


    return null;
  }
}
