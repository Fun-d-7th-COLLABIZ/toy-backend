package collabiz.toy.controller.MyPost;

import collabiz.toy.service.MyPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * by.dahae
 * post 생성 로직 꼬임. 다시 설계
 */

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/mypost")
public class MypostController {
    private final MyPostService myPostService;


    @ResponseBody
    @PostMapping
    public String addMyPost(@RequestBody MyPostData myPostData){
        log.info("map ok");
        return "ok";//나중에 수정
    }

    @ResponseBody
    @DeleteMapping("/{mypostid}")
    public String deleteMyPost(@PathVariable("mypostid") String data){
        log.info("delete id={}", data);
        return "delete id=" + data;//나중에 수정
    }

    @ResponseBody
    @GetMapping("/search")
    public MyPostSearchResult searchMyPost(Long id, @PageableDefault(page = 1) Pageable pageable, String title) {
        return myPostService.searchMyPost(id, title, pageable);
    }
}
