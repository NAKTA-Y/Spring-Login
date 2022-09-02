package hello.itemservice.domain.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class Member {

    private Long id;

    private String loginId;

    private String password;

    private String nickname;

    public Member(String loginId, String password, String nickname) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
    }
}
