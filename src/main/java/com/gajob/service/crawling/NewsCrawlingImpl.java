package com.gajob.service.crawling;

import com.gajob.dto.NewsDto;
import com.gajob.dto.NewsResponseDto;
import com.gajob.entity.News;
import com.gajob.repository.NewsRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NewsCrawlingImpl implements NewsCrawling {

  // 연합뉴스 취업/창업 카테고리 기사 크롤링
  private final NewsRepository newsRepository;

  private final String URL = "https://www.yna.co.kr/economy/job-foundation";
  private final String PAGE = "";

  @Override
  public String getNewsUrl(int page) {
    return URL + "?" + PAGE + page;
  }

  // Jsoup을 이용하여 사이트 내 데이터 추출
  @Override
  public List<News> getNewsData(String URL) throws IOException {
    List<News> newsList = new ArrayList<>();

    // 전체 html 코드
    Document document = Jsoup.connect(URL).get();

    // 기사 제목 가져오기
    Elements titleElements = document.select("ul.list strong.tit-news");

    // 기사 내용 가져오기
    Elements contentsElements = document.select("ul.list p.lead");

    // 기사 업로드 시간 및 날짜 가져오기
    Elements createTimeElements = document.select("ul.list span.txt-time");

    // 기사 URL 가져오기
    Elements urlElements = document.select("ul.list div.news-con a");

    // 기사 이미지 URL 가져오기
    Elements imgUrlElements = document.select("ul.list figure.img-con a img");

    for (int i = 0; i < contentsElements.size(); i++) {
      // titleElements 추출
      String title = titleElements.get(i).text();
      // contentsElements 추출
      String contents = contentsElements.get(i).text();
      // createTimeElements 추출
      String createTime = createTimeElements.get(i).text();
      // 기사 URL 추출
      String url = urlElements.get(i).getElementsByAttribute("href").attr("href");
      // 기사 이미지 URL 추출
      String imgUrl = imgUrlElements.get(i).getElementsByAttribute("src").attr("src");

      News news = new News(title, contents, createTime, url.substring(2), "https:" + imgUrl.replace(".jpg",""));
      newsList.add(news);
    }
    return newsList;
  }

  // Crawling한 뉴스 정보들을 DB에 저장한다.
  @Transactional
  @Override
  public NewsDto saveNews(NewsDto newsDto) throws Exception {
    List<News> list = new ArrayList<>();

    int page = 1;

    // 반복문을 통해서 5페이지까지의 정보를 가져온다. (20페이지까지 있으나 너무 많은 데이터를 가져올 필요가 없으므로 적당량만 추출)
    for (int i = 1; i <= 5; i++) {
      String url = getNewsUrl(i);
      List<News> newsData = getNewsData(url);

      // for 문을 통해서 getNewsData 메소드를 통해 받아온 데이터들을 newsRepository에 저장한다.
      for (News news : newsData) {
        newsRepository.save(news);
      }
    }
    return null;
  }

  // DB에 저장한 뉴스 정보 읽어오기
  @Transactional
  @Override
  public List<NewsResponseDto> getNews() {
    List<NewsResponseDto> newsResponseDtos = new ArrayList<>();
    for (News news : newsRepository.findAll()) {
      NewsResponseDto newsResponseDto = new NewsResponseDto(news);
      newsResponseDtos.add(newsResponseDto);
    }
    return newsResponseDtos;
  }

  // getNewsData() 메소드를 호출하여 allNews List에 저장을 한다.
//  public List<News> crawling() throws IOException {
//    List<News> allNews = new ArrayList<>();
//
//    int page = 1;
//
//    while (true) {
//      String url = getNewsUrl(page++);
//      List<News> newsData = getNewsData(url);
//
//      if (newsData.size() == 0) {
//        break;
//      }
//      allNews.addAll(newsData);
//    }
//    return allNews;
//  }
}
