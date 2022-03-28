package collabiz.toy.controller.Member;


import collabiz.toy.entity.Member;
import collabiz.toy.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * by.dahae
 */


@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping(value = "/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "회원가입페이지";
    }

    @PostMapping(value = "/members/new")
    public String create(@Valid MemberForm form, BindingResult result) { //DTO를 사용하여 엔티티 값 setting
        if (result.hasErrors()) {
            return "회원가입페이지로 redirect";
        }

        Member member = new Member();
        member.setName(form.getName());
        member.setEmail(form.getEmail());
        memberService.join(member); //DB에 저장
        return "redirect:/";
    }

    @DeleteMapping("/mypage/account")
    public String delete(Long id) {
        memberService.delete(id);
        return "redirect:/";
    }
}
