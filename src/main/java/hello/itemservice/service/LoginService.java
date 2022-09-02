package hello.itemservice.service;

import hello.itemservice.domain.member.Member;
import hello.itemservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String loginId, String password) {

        return memberRepository.findByLoginId(loginId)
                .filter(member -> member.getPassword().equals(password))
                .orElse(null);
    }
}
