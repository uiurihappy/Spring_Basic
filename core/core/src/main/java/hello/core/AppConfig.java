package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//애플리케이션의 전체를 구성하고 설정하는 클래스
public class AppConfig {

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()
    //총 2번 호출
    @Bean
    // 네 가지의 구현 객체를 생성한다.(MemberServiceImpl, MemoryMemberRepository, OrderServiceImpl, FixDiscountPolicy)
    public MemberService memberService(){
        //MemberService를 불러다가 사용
        //MemberServiceImpl을 통해 생성자를 통해서 주입
        System.out.println("Call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("Call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    
    @Bean
    public OrderService orderService(){
        //MemoryMemberRepository와 FixDiscountPolicy 두개가 들어간다.
        //OrderServiceImpl을 통해 생성자를 통해서 주입
        System.out.println("Call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
        //return null;
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        //구성 영역에만 영향있고 사용 영역에는 영향이 없다.
        return new RateDiscountPolicy();
    }

}
