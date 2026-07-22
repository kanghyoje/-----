package kr.hs.dgsw.ex.sample;

import lombok.ToString;import org.springframework.stereotype.Component;import org.springframework.web.bind.annotation.RestController;

@ToString
@Component //빈 등록(정의)
public class KakaoPayService implements PayService{
    
    @Override
    public long pay(long amount) {
        return amount;
    }
}
