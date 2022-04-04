package com.gajob.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class News {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title; //기사 제목

  private String contents; //기사 내용

  private String createTime; //기사 업로드 날짜 및 시간

  private String url; //기사 URL

  private String imgUrl; //이미지 URL

  public News(String title, String contents, String createTime, String url, String imgUrl) {
    this.title = title;
    this.contents = contents;
    this.createTime = createTime;
    this.url = url;
    this.imgUrl = imgUrl;
  }
}
