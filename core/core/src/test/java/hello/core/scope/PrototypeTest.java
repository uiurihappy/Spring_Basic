package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class PrototypeTest {
    @Test
    void prototypeBeanFind(){
        //스프링 컨테이너
        //컴포넌트를 지정해주면(Prototype) 자동으로 컴포넌트 스캔 대상이 된다.
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("find prototypeBean1");
        //SingletonBean.init으로 만들어진다.
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        //SingletonBean.init으로 새로운 객체가 만들어진다.
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        //서로 다른 참조 값인 걸 확인할 수 있다.
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        //close가 안되고 있다. 정말 프로토타입이기 때문에
        ac.close();
    }
    @Scope("prototype")
    static class PrototypeBean{
        @PostConstruct
        public void init(){
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("SingletonBean.destory");
        }
    }
}
