package kr.hs.dgsw.ex.sample;

import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@ToString
@SpringBootTest
class PayServiceTest {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ApplicationContext ac; //스프링 컨테이너(IoC컨테이너)

    @Test
    void test3() {
        PayService bean = ac.getBean(PayService.class);
        System.out.println("==========");
        System.out.println(bean instanceof KakaoPayService);
        KakaoPayService kakaoPayService = (KakaoPayService) bean;
        System.out.println(kakaoPayService);
    }
    @Test
    void test() {
        System.out.println("===================");
        System.out.println(orderService);
    }

    @Test
    void test2() {
        long amount = orderService.order(1_000L);
        System.out.println("============");
        System.out.println(amount);
    }


}