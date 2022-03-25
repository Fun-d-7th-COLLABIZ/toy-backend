package collabiz.toy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/mypost")
public class MypostController {


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

}
