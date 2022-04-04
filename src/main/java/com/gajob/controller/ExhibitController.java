package com.gajob.controller;

import com.gajob.dto.ExhibitDto;
import com.gajob.entity.Exhibit;
import com.gajob.service.crawling.ExhibitCrawling;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/issue")
public class ExhibitController {

    private final ExhibitCrawling exhibitCrawling;

    @GetMapping("/exhibit")
    public List<Exhibit> getExhibitData(String url) throws IOException {
        return exhibitCrawling.getExhibitData(exhibitCrawling.getExhibitUrl(1));
    }

    @PostMapping("/save") // 크롤링한 데이터 DB 저장
    public ResponseEntity<ExhibitDto> exhibitSave() throws Exception {
        return ResponseEntity.ok(exhibitCrawling.exhibitSave(new ExhibitDto(Exhibit.builder().build())));
    }
}
