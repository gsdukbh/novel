package top.werls.novel.common.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Jiawei Lee
 * @version TODO date created 2022/7/24
 * @since on
 */
@Data()
@EqualsAndHashCode(callSuper = true)
@Schema(description = "book")
public class Book extends BaseEntity implements Serializable {

  private long id;

  @Schema(description = "书名")
  private String name;
  private String author;
  private String description;
  private String url;
  private  String img;

}
