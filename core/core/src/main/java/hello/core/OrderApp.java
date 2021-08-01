package hello.core;

import hello.core.member.*;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    //psvm
    public static void main(String[] args) {
        /*
        AppConfig appConfig = new AppConfig();

        MemberService memberService = appConfig.memberService();
        //MemoryMemberRepository와 FixDiscountPolicy 객체를 참조하도록
        OrderService orderService = appConfig.orderService();
        */

        //스프링 컨테이너
        //AppConfig에 있는 환경 설정 정보를 가지고 스프링이 @Bean 에노테이션들을 스프링 빈에 집어넣어서 관리를 해준다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //스프링 컨테이너를 통해 찾아온다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);


        //회원 저장
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        //회원 객체를 저장
        memberService.join(member);

        //itemA라는 10000원 짜리 상품을 주문
        Order order = orderService.createOrder(memberId, "itemA", 20000);

        System.out.println("order = " + order.toString());          //order 정보
        System.out.println("order = " + order.calculatePrice());    //할인 정보
    }
}
