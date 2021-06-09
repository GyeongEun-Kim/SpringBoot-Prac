package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**회원가입**/
    public  long join (Member member) {

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) { //같은 이름의 중복회원은 안됨
        memberRepository.findByName(member.getName())
            .ifPresent(m-> { //ifPresent:값이 존재하면(널이 아니면)
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });

    }

    /**전체회원 조회**/
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
