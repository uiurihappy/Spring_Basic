package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//구현체
public class MemberServiceImpl implements MemberService{

    //저장 구현체
    //할당하는 부분이 구현체를 의존하여 DIP 위반
    //MemberServiceImpl은 MemberRepository, MemoryMemberRepository 둘 다 의존한다.
    private final MemberRepository memberRepository;

    @Autowired //자동 의존관계 주입 //ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        //새로 할당(추상화에만 의존하여 DIP를 지킨다.)
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        //다형성에 의해서 save를 호출하여 회원 등록
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        //다형성에 의해서 findById를 호출하여 회원 조회
        return memberRepository.findById(memberId);
    }

    //test 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
