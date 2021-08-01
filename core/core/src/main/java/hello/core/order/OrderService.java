package hello.core.order;

public interface OrderService {
    //클라이언트에 주문을 생성하고 주문 결과를 반환하는 역할
    Order createOrder(Long memberId, String itemName, int itemPrice);

}
