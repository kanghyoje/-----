package kr.hs.dgsw.ex.repository;

import kr.hs.dgsw.ex.domain.Member;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void saveMember() {
        Member member
                = new Member("스프링", "spring!a.com");
        System.out.println(member.getId());
        Member saveMember
                = memberRepository.save(member);
        System.out.println("===========");
        System.out.println(saveMember);
    }
    @Test
    void findById() {
        Optional<Member> result
                = memberRepository.findById(9L);

        if(result.isPresent()) {
            Member member = result.get();
            System.out.println(member);
        }
    }
}