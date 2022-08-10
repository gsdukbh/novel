package top.werls.novel.common.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author Jiawei Lee
 * @version TODO date created 2022/7/24
 * @since on
 */
@Setter
@Getter
@ToString
@Entity
public class BookChapter extends BaseEntity implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  /** 图书id */
  private Integer bid;

  private String name;
  /** 文本内容 */
  private String content;

  private String url;
  /** 字数 */
  private Integer length;
  private  boolean isCache;
}
