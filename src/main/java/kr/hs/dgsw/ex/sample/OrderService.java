package kr.hs.dgsw.ex.sample;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final PayService payService;

//    @Autowired


    public long order(long amount) {
        return payService.pay(amount);
    }
}
