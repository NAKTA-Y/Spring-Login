package hello.itemservice.repository;

import hello.itemservice.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member memberA = new Member("memberA", "memberA", "1234");
        Member memberB = new Member("memberB", "memberB", "1234");

        //when
        Member savedMemberA = memberRepository.save(memberA);
        Member savedMemberB = memberRepository.save(memberB);

        //then
        Optional<Member> findMemberA = memberRepository.findByLoginId("memberA");
        Optional<Member> findMemberB = memberRepository.findByLoginId("memberB");

        assertThat(findMemberA.get()).isEqualTo(savedMemberA);
        assertThat(findMemberB.get()).isEqualTo(savedMemberB);
    }

    @Test
    void findAll() {
        // given
        Member memberA = new Member("memberA", "memberA", "1234");
        Member memberB = new Member("memberB", "memberB", "1234");

        Member savedMemberA = memberRepository.save(memberA);
        Member savedMemberB = memberRepository.save(memberB);

        // when
        List<Member> members = memberRepository.findAll();

        // then
        assertThat(members.size()).isEqualTo(2);
        assertThat(members.get(0)).isEqualTo(savedMemberA);
        assertThat(members.get(1)).isEqualTo(savedMemberB);
    }

    @Test
    void findByMemberId() {
        // given
        Member memberA = new Member("memberA", "memberA", "1234");

        Member savedMemberA = memberRepository.save(memberA);

        // when
        Member findByIdMember = memberRepository.findById(1L);
        Optional<Member> findByLoginIdMember = memberRepository.findByLoginId("memberA");

        //then
        assertThat(findByLoginIdMember.get()).isEqualTo(findByIdMember);
        assertThat(savedMemberA).isEqualTo(findByIdMember);
    }
}