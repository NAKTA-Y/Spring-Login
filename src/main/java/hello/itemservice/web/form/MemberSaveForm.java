package hello.itemservice.web.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberSaveForm {

    @NotBlank
    private String loginId;

    @NotEmpty
    private String nickname;

    @NotEmpty
    private String password;
}
