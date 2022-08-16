package top.werls.novel.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class CrawlEncode extends BaseEntity {
  @Serial private static final long serialVersionUID = 2L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  /** 站点 */
  private String site;
  /** 图书名称 的选择器 <a href="https://jsoup.org/cookbook/extracting-data/selector-syntax">see more</a> */
  private String bookNameSelect;
  /** 图书名称 选取的 item index */
  private Integer bookNameIndex;
  /**
   * 图书名称 Element data 类型 <a href="https://jsoup.org/cookbook/extracting-data/dom-navigation">see
   * more</a>
   */
  private String bookNameType;
  /** 作者名称的 的选择器 */
  private String authorSelect;
  /** 作者名称的 item index */
  private Integer authorIndex;

  private String authorType;
  private String descriptionSelect;
  private Integer descriptionIndex;
  private Integer descriptionType;
  private String chapterListSelect;
  private Integer chapterListIndex;
  private String chapterListType;
  /** 需要分页 获取的数据 情况 */
  private boolean chapterListPages;

  private String chapterContentSelect;
  private Integer chapterContentIndex;
  private String chapterContentType;
}
