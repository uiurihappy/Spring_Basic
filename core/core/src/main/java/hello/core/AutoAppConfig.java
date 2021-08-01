package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

//컴포넌트 스캔: 스프링 빈을 긁어모아서 자동으로 스프링 빈으로 끌어올려야 한다.
@Configuration
@ComponentScan(
        //basepackage가 지정한 곳부터 찾아 탐색
        //basePackages = "hello.core.member",
        //지정을 하지 않으면 디폴트부터 탐색해서 시간이 오래걸리고 복잡하다. ex) hello.core부터
        //지정한 클래스의 패키지를 탐색 시작 위로 지정한다.
        //basePackageClasses = AutoAppConfig.class,
        //스프링 빈에 등록하기 전에 필터링해서 등록한다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
) //Component 라는 Annotation 붙은 클래스들을 전부 긁어서 스프링 빈에 등록해준다.
public class AutoAppConfig {
/*
    @Autowired MemberRepository memberRepository;
    @Autowired
    DiscountPolicy discountPolicy;

    @Bean
    OrderService orderService(MemberRepository memberRepository, DiscountPolicy discountPolicy){
        return new OrderServiceImpl(memberRepository, discountPolicy);
    }
*/
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//
//    }
}


