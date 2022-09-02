package hello.itemservice.controller;

import hello.itemservice.domain.member.Member;
import hello.itemservice.repository.MemberRepository;
import hello.itemservice.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

//    @GetMapping("/")
//    public String home() {
//        return "home";
//    }

    @GetMapping("/")
    public String homeLogin(HttpServletRequest request, Model model) {

        Member member = (Member)sessionManager.getSession(request);

        if (member == null) {
            return "/home";
        }

        model.addAttribute("member", member);
        return "/loginHome";
    }
}
