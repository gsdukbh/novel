package top.werls.novel.common.entity;

import jakarta.persistence.*;
import lombok.*;


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
@Table(name = "BookChapter", indexes = {
        @Index(name = "idx_bookchapter_name", columnList = "name")
})
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
  /** 章节序号 */
  private Integer number;

  private String hash;
  private boolean isCache;
}
