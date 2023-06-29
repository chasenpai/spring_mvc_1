package com.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@Slf4j
public class RequestHeaderController {

    /**
     * 어노테이션 기반의 스프링 컨트롤러는 다양한 파라미터를 지원한다
     * - HttpMethod : HTTP 메서드를 조회
     * - @RequestHeader : 모든 HTTP 헤더를 MultiValueMap 형식으로 조회
     * - @RequestHeader : 특정 HTTP 헤더를 조회
     * - @CookieValue : 특정 쿠키를 조회
     */
    @RequestMapping("/headers")
    public String headers(HttpServletRequest request, HttpServletResponse response, HttpMethod httpMethod, Locale locale,
                          @RequestHeader MultiValueMap<String, String> headerMap, @RequestHeader("host") String host,
                          @CookieValue(value = "myCookie", required = false) String cookie){

        log.info("request = {}", request);
        log.info("response = {}", response);
        log.info("httpMethod = {}", httpMethod);
        log.info("locale = {}", locale);
        log.info("headerMap = {}", headerMap);
        log.info("header host = {}", host);
        log.info("myCookie = {}", cookie);

        return "ok";
    }

}
