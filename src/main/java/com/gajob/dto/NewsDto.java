package com.gajob.dto;

import com.gajob.entity.News;
import lombok.Data;

@Data // 생성자, Getter, Setter, toString 등을 자동으로 생성해준다.
public class NewsDto {

  //  private Long id;
  private String title;
  private String contents;
  private String createTime;
  private String url;
  private String imgUrl;

  public NewsDto(News news) {
//    this.id = news.getId();
    this.title = news.getTitle();
    this.contents = news.getContents();
    this.createTime = news.getCreateTime();
    this.url = news.getUrl();
    this.imgUrl = news.getImgUrl();
  }

}
