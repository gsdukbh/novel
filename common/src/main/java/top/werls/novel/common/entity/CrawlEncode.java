package top.werls.novel.common.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 使用数据库 自定义 解析数据
 *
 * @author Jiawei Lee
 * @version TODO
 * @date Date: 2022/8/11
 * @since on
 */
@Setter
@Getter
@Entity
@Table(
    name = "crawl_encode",
    indexes = {@Index(name = "idx_crawl_encode_site", columnList = "site")})
public class CrawlEncode extends BaseEntity implements Serializable {
  @Serial private static final long serialVersionUID = 2L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;


  @Column(unique = true)
  /** 站点 */
  private String site;
  /** 图书名称 的选择器
   * <a href="https://jsoup.org/cookbook/extracting-data/selector-syntax">see more</a>
   * */
  private String bookNameSelect;
  /** 图书名称 选取的 item index */
  private Integer bookNameIndex;
  /**
   * 图书名称 Element data 类型
   * <a href="https://jsoup.org/cookbook/extracting-data/dom-navigation">see
   * more</a>
   *
   */

  private String bookNameType;

  /**
   * attr 类型时 的keg
   */
  private String bookNameTypeKey;
  /** 作者名称的 的选择器 */
  private String authorSelect;
  /** 作者名称的 item index */
  private Integer authorIndex;

  /**
   * item 的类型 一般是 attr text html
   */
  private String authorType;
  /**
   * 类型时 attr 的key
   */
  private String authorTypeKey;
  private String descriptionSelect;
  private Integer descriptionIndex;
  private String descriptionType;
  private String descriptionTypeKey;
  private String chapterListSelect;
  private String bookImgSelect;
  private Integer bookImgIndex;
  private String bookImgType;
  private String bookImgTypeKey;
  /** 不是一次性展示 所有目录 */
  private boolean isTwoClick;

  private String twoClickUrlSelect;
  private Integer twoClickUrlSelectIndex;

  /** 需要分页 获取的数据 情况 */
  private boolean chapterListPages;

  private String chapterListPagesUrlSelect;

  /** 章节内容部分 选择器 */
  private String chapterContentSelect;

  private Integer chapterContentIndex;
  private String chapterContentType;
  private String chapterContentTypeKey;
  /** 章节名称 */
  private String chapterNameSelect;

  private Integer chapterNameIndex;
  private String chapterNameType;
  private String chapterNameTypeKey;
}
