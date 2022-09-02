package hello.itemservice.web.session;

import hello.itemservice.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SessionManagerTest {

    @Autowired SessionManager sessionManager;

    @Test
    void sessionTest() {
        // given
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        Member member = new Member("memberA", "memberA", "1234");

        // when
        sessionManager.createSession(member, response);
        request.setCookies(response.getCookies());

        // then
        Object result = sessionManager.getSession(request);
        assertThat(result).isEqualTo(member);
    }

    @Test
    void expireTest() {
        // given
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        Member member = new Member("memberA", "memberA", "1234");

        // when
        sessionManager.createSession(member, response);
        request.setCookies(response.getCookies());

        // then
        sessionManager.expire(request);
        Object result = sessionManager.getSession(request);
        assertThat(result).isEqualTo(null);
    }
}