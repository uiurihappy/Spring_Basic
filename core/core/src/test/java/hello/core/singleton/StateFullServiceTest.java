package hello.core.singleton;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StateFullServiceTest {

    @Test
    void stateFullServiceSingleton(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StateFullService stateFullService1 = ac.getBean(StateFullService.class);
        StateFullService stateFullService2 = ac.getBean(StateFullService.class);

        //ThreadA = A 사용자가 10000원을 주문
        //지역변수
        int UserAPrice = stateFullService1.order("userA", 10000);

        //ThreadB = B 사용자가 20000원을 주문
        //지역변수
        int UserBPrice = stateFullService2.order("userB", 20000);

        //ThreadA: 사용자 A가 주문 금액을 조회
        //int price = stateFullService1.getPrice();
        System.out.println("price = " + UserAPrice);

        //assertThat(stateFullService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{

        @Bean
        public StateFullService stateFullService(){
            return new StateFullService();
        }
    }

}
