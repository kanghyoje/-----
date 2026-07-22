package kr.hs.dgsw.ex.controller;

import jakarta.annotation.PostConstruct;
import kr.hs.dgsw.ex.dto.request.MemberPageRequest;
import kr.hs.dgsw.ex.dto.request.MemberRequest;
import kr.hs.dgsw.ex.dto.response.MemberResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final Map<Long, MemberResponse> members = new HashMap<>();

    private final AtomicLong seq = new AtomicLong();

    @GetMapping
    public List<MemberResponse> list(
            MemberPageRequest request
    ) {
        // 컬렉션 + 람다(함수형 인터페이스_ + 스트림

        int page = Math.max(request.getPage() - 1, 0);
        //스트림 (스트림 데이터 -> 중간 연산 -> 최종연산)
        return members.values().stream()
                .skip(page * request.getSize())
                .limit(request.getSize())
                .toList();
    }

    @GetMapping("/{id}")
    public MemberResponse get(@PathVariable Long id){
        MemberResponse memberResponse = members.get(id);
        if(memberResponse == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "회원 없음 :" + id);
        }
        return memberResponse;
    }

    @GetMapping("/search")
    public List<MemberResponse> search(
            @RequestParam(value = "name",defaultValue = "") String name
    ){
        return members.values()
                .stream()
                .filter(memberResponse -> memberResponse.getName().contains(name))
                .toList();
    }

    @PostMapping
    public ResponseEntity<MemberResponse> create(
            @RequestBody MemberRequest request
    ) {
        MemberResponse saved
                = save(request.getName(), request.getEmail());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @PutMapping("{id}")
    public MemberResponse update(
            @PathVariable long id,
            @RequestBody MemberRequest request
    ) {
        if(!members.containsKey(id)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "회원 없음 :" + id);
        }
        MemberResponse updated
                = new MemberResponse(id,
                request.getName(),
                request.getEmail());
        members.put(id, updated);
        return updated;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void delete(@PathVariable long id) {
        if(!members.containsKey(id)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "회원 없음 :" + id);
        }

        members.remove(id);
    }

    @PostConstruct
    public void init() {
//        save("김스프링","kim@a.com");
//        save("박부트","kimn@a.com");
//        save("ㄴㄴㄴ","kima@a.com");
        IntStream
                .rangeClosed(0,100)
                .forEach(i -> save("자바" + i, "lee" + i +"@a.com"));

    }
    private MemberResponse save(String name, String email){
        long id = seq.getAndIncrement();
        MemberResponse member = new MemberResponse(id,name,email);
        members.put(id, member);
        return member;
    }
}
