package hello.core.scope;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int cnt1 = clientBean1.logic();
        assertThat(cnt1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int cnt2 = clientBean2.logic();
        assertThat(cnt2).isEqualTo(1);
    }

    @Scope("singleton")
    //@RequiredArgsConstructor
    static class ClientBean{
        //private final PrototypeBean prototypeBean; //생성 시점에 주입

        //필드 주입
        @Autowired
        //getObject 뿐만 아니라 다른 기능들도 제공
        //private ObjectProvider<PrototypeBean> prototypeBeanProvider;
        //getObject 기능만 제공
        //private ObjectFactory<PrototypeBean> prototypeBeanProvider;
        private Provider<PrototypeBean> prototypeBeanProvider;
        public int logic() { //여기에 있는 프로토타입 빈은 생성 시점에 주입된 프로토타입을 사용
            //getObject를 호출하면 그때서야 스프링 컨테이너에서 프로토타입 빈을 찾아서 반환해준다.
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount(); //반환된 count = 1 을 addCount한다.
            int count = prototypeBean.getCount();
            return count;
        }

        //프로토타입 빈 내놔!
//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean){
//            this.prototypeBean = prototypeBean;
//        }

    }

    @Scope("prototype")
    static class PrototypeBean{

        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init: "+ this);
        }

        @PreDestroy
        public void destory(){
            System.out.println("PrototypeBean.destroy");
        }

    }

}
