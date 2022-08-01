package top.werls.novel.common.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Jiawei Lee
 * @version TODO date created 2022/7/24
 * @since on
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BookChapter extends BaseEntity implements Serializable  {

  @Serial private static final long serialVersionUID = 1L;
  private long id;
  /**
   * 图书id
   */
  private long bid;
  private String name;
  private String content;
  private String url;
}
