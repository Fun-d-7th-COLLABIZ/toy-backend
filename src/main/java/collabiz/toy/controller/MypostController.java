package collabiz.toy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MypostController {

    @PostMapping("/mypost")
    public String helloPost(){
        log.info("map ok");
        return "ok";//나중에 수정
    }

   @DeleteMapping("/mypost/{mypostid}")
    public String helloPost(@PathVariable("mypostid") String data){
        log.info("delete id={}", data);
        return "ok";//나중에 수정
    }

}
