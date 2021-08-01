package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor    //클래스의 생성자를 자동적으로 생성해
public class OrderServiceImpl implements OrderService{

    // 한번 생성하면 불변이다.
    //OrderService에서 필요한것 회원 저장소와 할인 정책에서 고정 할인정책이 필요하다.
    private final MemberRepository memberRepository;
    // 인터페이스에만 의존한다.
    private final DiscountPolicy discountPolicy;

    @Autowired
    private DiscountPolicy rateDiscountPolicy;

    //수정자 주입을 위해 final을 지우고 set메서드 작성
    //set대문자 관례
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        //System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        //System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }

    //순서 보장없이 의존 관계를 주입한다.
    @Autowired
    //AppConfig에서 MemoryMemberRepository와 FixDiscountPolicy 두개가 들어간다.
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        //생성자 setting 필수!
        //System.out.println("memberRepository = " + memberRepository);
        //System.out.println("discountPolicy = " + discountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }


    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //OCP 위반
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        //단일 체계 원칙
        Member member = memberRepository.findById(memberId);            //1) id를 찾는다.
        int discountPrice = discountPolicy.discount(member, itemPrice); //2) 회원과 아이템 가격을 넘겨준다.
        //최종 생성된 주문 반환
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //test 용도
    public MemberRepository getMemberRepository() { return memberRepository; }


}
