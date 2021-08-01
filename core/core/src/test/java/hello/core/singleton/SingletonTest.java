package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        //1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();


        //2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);


        //memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);

    }

    @Test
    @DisplayName("싱클톤 패턴을 적용한 객체 사용")
    public void singletonServiceTest(){

        //private으로 생성자를 막아두었다. 컴파일 오류가 발생한다.
        //new SingletonService();
        //호출할 때 마다 같은 객체 인스턴스를 반환하는 것을 확인할 수 있다.
        //1. 조회: 호출할 때 마다 같은 객체를 반환
        SingletonService singletonService1 = SingletonService.getInstance();
        //2. 조회: 호출할 때 마다 같은 객체를 반환
        SingletonService singletonService2 = SingletonService.getInstance();

        //참조값이 같은 것을 확인
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);
        // singletonService1 == singletonService2

        assertThat(singletonService1).isSameAs(singletonService2);
        //same: ==
        //equal: 자바의 Equals 메소드 오버라이딩
        singletonService1.logic();

        // 싱글톤 패턴을 적용하면 고객의 요청이 올 때 마다 객체를 생성하는 것이 아니라,
        // 이미 만들어진 객체를 공유해서 효율적으로 사용할 수 있다.


    }
    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        //AppConfig appConfig = new AppConfig();

        //스프링으로 바꾼다.
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //1. 조회: 호출할 때 마다 같은 객체를 반환
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        //2. 조회: 호출할 때 마다 같은 객체를 반환
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //참조값이 같은 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        //memberService1 == memberService2
        assertThat(memberService1).isSameAs(memberService2);
    }

}