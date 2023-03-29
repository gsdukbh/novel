package top.werls.novel.crawl.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import top.werls.novel.common.entity.BaseEntity;

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
@Table(name = "crawl_encode", indexes = {
    @Index(name = "idx_crawl_encode_site", columnList = "site")
})
public class CrawlEncode extends BaseEntity implements Serializable {
  @Serial private static final long serialVersionUID = 2L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;


  @NotNull(message = "不能为空")
  @Column(unique = true)
  /** 站点 */
  private String site;
  /** 图书名称 的选择器
   * <a href="https://jsoup.org/cookbook/extracting-data/selector-syntax">see more</a>
   * */
  @NotNull(message = "不能为空")
  private String bookNameSelect;

  /** 图书名称 选取的 item index */
  @NotNull(message = "不能为空")
  private Integer bookNameIndex;
  /**
   * 图书名称 Element data 类型
   * <a href="https://jsoup.org/cookbook/extracting-data/dom-navigation">see
   * more</a>
   *
   */

  @NotNull
  private String bookNameType;

  /**
   * attr 类型时 的keg
   */
  @NotNull
  private String bookNameTypeKey;
  /** 作者名称的 的选择器 */
  @NotNull
  private String authorSelect;
  /** 作者名称的 item index */
  @NotNull
  private Integer authorIndex;

  /**
   * item 的类型 一般是 attr text html
   */
  @NotNull
  private String authorType;
  /**
   * 类型时 attr 的key
   */
  @NotNull
  private String authorTypeKey;
  /**
   * 详情选择器
   */
  @NotNull
  private String descriptionSelect;

  /**
   *
   */
  @NotNull
  private Integer descriptionIndex;
  @NotNull
  private String descriptionType;
  @NotNull
  private String descriptionTypeKey;
  @NotNull
  private String chapterListSelect;
  @NotNull
  private String bookImgSelect;
  @NotNull
  private Integer bookImgIndex;
  @NotNull
  private String bookImgType;
  @NotNull
  private String bookImgTypeKey;
  /** 不是一次性展示 所有目录 */
  private boolean isTwoClick;

  private String twoClickUrlSelect;
  private Integer twoClickUrlSelectIndex;

  /** 需要分页 获取的数据 情况 */
  private boolean chapterListPages;

  private String chapterListPagesUrlSelect;

  /** 章节内容部分 选择器 */
  @NotNull
  private String chapterContentSelect;

  @NotNull
  private Integer chapterContentIndex;
  @NotNull
  private String chapterContentType;
  @NotNull
  private String chapterContentTypeKey;
  /** 章节名称 */
  @NotNull
  private String chapterNameSelect;

  @NotNull
  private Integer chapterNameIndex;
  @NotNull
  private String chapterNameType;
  @NotNull
  private String chapterNameTypeKey;
}
