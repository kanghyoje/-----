package kr.hs.dgsw.ex.dto.request;

import lombok.*;

@ToString
@Setter
@Getter
public class MemberPageRequest {
    private int page;
    private int size;
    private String name;

    public MemberPageRequest() {
        this.page = 1;
        this.size = 20;
    }
}
