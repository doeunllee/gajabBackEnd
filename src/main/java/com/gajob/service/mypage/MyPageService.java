package com.gajob.service.mypage;

import com.gajob.dto.posts.PostsReadDto;
import com.gajob.dto.study.StudyReadDto;
import java.util.List;

public interface MyPageService {

  List<PostsReadDto> getUserAllPosts(); //사용자가 작성한 JOB담 커뮤니티 게시물 전체 조회

  List<StudyReadDto> getUserAllStudy(); //사용자가 작성한 스터디 매칭 시스템 커뮤니티 게시물 전체 조회

}
