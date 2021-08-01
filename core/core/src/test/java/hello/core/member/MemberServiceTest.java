package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join(){
        //given : 어떤 상황이 주어졌을때
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when : 이렇게 했을 때
        memberService.join(member);
        //2L이면 에러
        Member findMember = memberService.findMember(1L);

        //then : 이렇게 된다.
        //assertThat: 둘이 맞는 지, 맞으면 패스
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
