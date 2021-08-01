package hello.core;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    //psvm
    public static void main(String[] args) {
        /*
        AppConfig appConfig = new AppConfig();
        //memberService안에는 memberServiceImpl이 들어가있다.
        MemberService memberService = appConfig.memberService();
        // 잘 되는지 테스트
        //MemberService memberService = new MemberServiceImpl();
        */
    
        //스프링 컨테이너
        //AppConfig에 있는 환경 설정 정보를 가지고 스프링이 @Bean 에노테이션들을 스프링 빈에 집어넣어서 관리를 해준다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //스프링 컨테이너를 통해 찾아온다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        // 조인
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        // 제대로 조인이 되었는지 확인!
        Member findmember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findmember = " + findmember.getName());

    }
}
