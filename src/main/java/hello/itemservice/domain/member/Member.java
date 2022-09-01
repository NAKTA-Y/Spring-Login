package hello.itemservice.domain.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class Member {

    private Long id;

    private String loginId;

    private String nickname;

    private String password;

    public Member(String loginId, String nickname, String password) {
        this.loginId = loginId;
        this.nickname = nickname;
        this.password = password;
    }
}
