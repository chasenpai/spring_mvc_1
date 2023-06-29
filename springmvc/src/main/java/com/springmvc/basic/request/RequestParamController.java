package com.springmvc.basic.request;

import com.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Controller
@Slf4j
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, age = {}", username, age);

        response.getWriter().write("ok");
    }

    @RequestMapping("/request-param-v2")
    @ResponseBody //뷰 조회를 무시하고 Http message body 에 직접 해당 내용 입력
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge){ //파라미터 이름으로 바인딩
        log.info("username = {}, age = {}", memberName, memberAge);
        return "ok";
    }

    @RequestMapping("/request-param-v3")
    @ResponseBody
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age){ //HTTP 파라미터 이름이 변수명과 같으면 name 생략 가능
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @RequestMapping("/request-param-v4")
    @ResponseBody
    public String requestParamV4(String username, int age){ //String, int 등 단순 타입이면 @RequestParam 생략 가능
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    /**
     * 위와 같이 어노테이션을 완전히 생략해도 되지만 너무 없는 것도 약관 과하다고 생각될 수 있다
     * @RequestParam 이 있으면 명확하게 요청 파라미터에서 데이터를 읽는 다는 것을 알 수 있다
     */
    @RequestMapping("/request-param-required")
    @ResponseBody
    public String requestParamRequired(@RequestParam(required = true) String username, //필수 유무, 없으면 400 예외 발생(빈 문자열 통과 가능)
                                       @RequestParam(required = false) Integer age){ //int 에 null 을 할당 하는 것은 불가능 > Integer 로 변경
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @RequestMapping("/request-param-default")
    @ResponseBody
    public String requestParamDefault(@RequestParam(required = true, defaultValue = "guest") String username, //기본값 설정(빈 문자열도 적용)
                                      @RequestParam(required = false, defaultValue = "-1") int age){
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @RequestMapping("/request-param-amp")
    @ResponseBody
    public String requestParamMap(@RequestParam Map<String, Object> paramMap){ //Map 으로 조회, 파라미터의 값이 1개가 확실할 때 사용 아닐땐 MultiValueMap
        log.info("username = {}, age = {}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @RequestMapping("/model-attribute-v1")
    @ResponseBody
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        /**
         * @ModelAttribute
         * - HelloData 객체를 생성하고 요청 파라미터의 이름으로
         * - HelloData 객체의 프로퍼리를 찾아 setter 를 호출하여 파라미터를 바인딩
         *
         * 프로퍼티
         * - 객체에 getXxx, setXxx 메서드가 있으면 xxx 이라는 프로퍼티를 가지고 있다
         * - xxx 프로터티의 값을 변경하면 setXxx 가 호출, 조회하면 getXxx 가 호출출         */
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        log.info("helloData = {}", helloData);

        return "ok";
    }

    @RequestMapping("/model-attribute-v2")
    @ResponseBody
    public String modelAttributeV2(HelloData helloData){ //생략 가능
        /**
         * 스프링은 생략 시 다음과 같은 규칙을 적용한다
         * - String, int, Intger 같은 단순 타입 = @RequestParma
         * - 나머지 = @ModelAttribute (argument resolver 로 지정해둔 타입 외)
         */
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        log.info("helloData = {}", helloData);

        return "ok";
    }

}
