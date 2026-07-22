package kr.hs.dgsw.ex.repository;

import kr.hs.dgsw.ex.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository
        extends JpaRepository<Member, Long> {

}
