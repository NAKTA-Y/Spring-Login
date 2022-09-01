package hello.itemservice.controller;

import hello.itemservice.domain.member.Member;
import hello.itemservice.repository.MemberRepository;
import hello.itemservice.web.form.MemberSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String memberAddForm(@ModelAttribute("member")Member member) {
        return "/members/add";
    }

    @PostMapping("/add")
    public String save(@Validated @ModelAttribute("member") MemberSaveForm saveForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/members/add";
        }

        String loginId = saveForm.getLoginId();
        String password = saveForm.getPassword();
        String nickname = saveForm.getNickname();


        Member member = new Member(loginId, password, nickname);

        memberRepository.save(member);

        return "redirect:/";
    }
}
