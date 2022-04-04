package com.gajob.dto;

import com.gajob.entity.News;
import lombok.Data;

@Data
public class NewsResponseDto {

  private Long id;
  private String title;
  private String contents;
  private String createTime;
  private String url;
  private String imgUrl;

  public NewsResponseDto(News news) {
    this.id = news.getId();
    this.title = news.getTitle();
    this.contents = news.getContents();
    this.createTime = news.getCreateTime();
    this.url = news.getUrl();
    this.imgUrl = news.getImgUrl();
  }
}
