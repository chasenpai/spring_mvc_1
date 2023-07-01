package com.springmvc.basic.response;

import com.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Controller
@Slf4j
//@RestController
public class ResponseBodyController {

    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2(){
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @GetMapping("/response-body-string-v3")
    @ResponseBody
    public String responseBodyV3(){
        return "ok";
    }

    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1(){
        HelloData helloData = new HelloData();
        helloData.setUsername("A");
        helloData.setAge(20);

        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    /**
     * @ResponseBody 사용 원리
     * - HTTP Body 에 문자 내용을 직접 반환
     * - viewResolver 대신 HttpMessageConverter 가 동작
     * - 기본 문자처리 : StringHttpMessageConverter
     * - 기본 객체처리 : MappingJacksonHttpMessageConverter
     */
    @GetMapping("/response-body-json-v2")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK) //응답 상태 코드 지정 어노테이션
    public HelloData responseBodyJsonV2(){
        HelloData helloData = new HelloData();
        helloData.setUsername("A");
        helloData.setAge(20);

        return helloData;
    }

    /**
     * RequestMappingHandlerAdapter 동작 방식
     * 1. ArgumentResolver
     * - 컨트롤러(핸들러)가 필요로 하는 다양한 파라미터의 값(객체)을 생성한다
     * - 파라미터의 값이 모두 준비되면 컨트롤러를 호출하면서 값을 넘겨준다
     * 2. ReturnValueHandler
     * - ArgumentResolver 와 비슷하나, 응답 값을 변환하고 처리
     * - 컨트롤러에서 String 으로 뷰 이름을 반환해도 동작하는게 이거 덕분
     */

}
