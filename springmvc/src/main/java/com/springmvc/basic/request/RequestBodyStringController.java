package com.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Controller
@Slf4j
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        InputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody = {}", messageBody);
        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody = {}", messageBody);
        responseWriter.write("ok");
    }

    /**
     * HttpEntity
     * - HTTP header, body 정보를 편하게 조회
     * - 응답에도 사용가능
     * - 메세지 바디 정보 직접 반환, 헤더 정보 포함 가능, view 조회 x
     *
     * RequestEntity & ResponseEntity
     * - HttpEntity 를 상속받은 객체들
     * - RequestEntity : HTTP method, url 정보 추가, 요청에서 사용
     * - ResponseEntity : HTTP 상태 코드 설정, 응답에서 사용
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        String messageBody = httpEntity.getBody();
        log.info("messageBody = {}", messageBody);
        return new HttpEntity<>("ok");
    }

    /**
     * @RequestBody
     * - HTTP 메세지 바디 정보를 편리하게 조회 가능, 헤더 정보 필요시 @RequestHeader 사용
     * - 메세지 바디를 직접 조회하는 기능은 요청 파라미터를 조회하는 @RequestParam 등과 전혀 관계 없음
     *
     * @ResponseBody
     * - 응답 결과를 HTTP 메세지 바디에 직접 담아서 전달할 수 있다. view 조회 x
     */
    @PostMapping("/request-body-string-v4")
    @ResponseBody
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {
        log.info("messageBody = {}", messageBody);
        return "ok";
    }

}
