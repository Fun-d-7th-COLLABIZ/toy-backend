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

    @PostMapping
    public String addPost(){
        log.info("map ok");
        return "ok";//나중에 수정
    }

   @DeleteMapping("/{mypostid}")
    public String deletePost(@PathVariable("mypostid") String data){
        log.info("delete id={}", data);
        return "delete id=" + data;//나중에 수정
    }

}
