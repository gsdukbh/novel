package top.werls.novel.common.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

/**
 * @author Jiawei Lee
 * @version TODO date created 2022/7/24
 * @since on
 */
@Getter
@Setter
@ToString
@Schema(description = "book")
@Entity
public class Book extends BaseEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Schema(description = "书名")
  private String name;

  private String author;
  private String description;
  private String otherInfo;
  private String url;
  private String img;
  private String hash;
  private boolean isCache;
}
