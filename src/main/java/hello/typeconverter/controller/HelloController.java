package hello.typeconverter.controller;

import hello.typeconverter.type.IpPort;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello-v1")
    public String helloV1(HttpServletRequest request) {
        String data = request.getParameter("data"); //문자 타입 조회 ?data=10 이여도 문자 "10"임
        Integer intValue = Integer.valueOf(data); // 숫자 타입으로 변환
        System.out.println("intValue = " + intValue);
        return "ok";
    }

    @GetMapping("/hello-v2") //스프링이 자동으로 내부에서 타입을 변환해줌 (WebConfig 등록 가능)
    public String helloV2(@RequestParam Integer data) { // "10" -> 10
        System.out.println("data = " + data);
        return "ok";
    }

    @GetMapping("/ip-port")
    public String ipPort(@RequestParam IpPort ipPort) {
        System.out.println("ipPort IP= " + ipPort.getIp());
        System.out.println("ipPort PORT = " + ipPort.getPort());
        return "ok";
    }


}
