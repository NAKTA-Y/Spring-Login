package hello.itemservice.controller;

import hello.itemservice.domain.member.Member;
import hello.itemservice.service.LoginService;
import hello.itemservice.web.form.LoginForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm) {

        return "/login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, HttpServletResponse response) {

        if (bindingResult.hasErrors()) {
            return "/login/loginForm";
        }

        Member member = loginService.login(loginForm.getLoginId(), loginForm.getPassword());

        if (member == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "/login/loginForm";
        }

        // 로그인 성공 TODO

        Cookie loginIdCookie = new Cookie("memberId", String.valueOf(member.getId()));
        response.addCookie(loginIdCookie);

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {

        expireCookie(response, "memberId");

        return "redirect:/";
    }

    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);

        response.addCookie(cookie);
    }
}
