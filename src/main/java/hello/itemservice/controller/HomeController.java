package hello.itemservice.controller;

import hello.itemservice.domain.member.Member;
import hello.itemservice.repository.MemberRepository;
import hello.itemservice.web.SessionConst;
import hello.itemservice.web.argumentresolver.Login;
import hello.itemservice.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    @GetMapping("/")
    public String homeLogin(@Login Member member, Model model) {

        if (member == null) {
            return "/home";
        }

        model.addAttribute("member", member);

        return "/loginHome";
    }
}
