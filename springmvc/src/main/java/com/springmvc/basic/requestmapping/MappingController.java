package com.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class MappingController {

    //배열로 매핑 다중 설정 가능
    @RequestMapping({"/hello-basic", "/hello-go"}) //HTTP method 가 없으면 모든 요청을 받음
    public String helloBasic(){
        log.info("helloBasic");
        return "ok";
    }

//    @RequestMapping(value = "/mapping-get-v2", method = RequestMethod.GET)
    public String mappingGetV1(){
        log.info("mapping-get-v1");
        return "ok";
    }

    //편리한 축약 어노테이션
    @GetMapping("/mapping-get-v2")
    public String mappingGetV2(){
        log.info("mapping-get-v2");
        return "ok";
    }

    /**
     * PathVariable(경로 변수)
     * - 최근 HTTP API 는 리소스 경로에 식별자를 넣는 스타일을 선호
     * - @PathVariable 을 사용하면 매칭 되는 부분을 편리하게 조회
     * - 변수명이 같으면 생략 가능
     * - 다중 사용 가능
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String userId){
        log.info("mappingPath userId = {}", userId);
        return "ok";
    }

    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId){
        log.info("mappingPath userId = {}, orderId = {}", userId, orderId);
        return "ok";
    }

    /**
     * 특정 파라미터 조건 매핑
     * params="mode",
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug","data=good"}
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam(){
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더 조건 매핑
     * headers="mode",
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug" (! = )
     */
    @GetMapping(value = "/mapping-param", headers = "mode=debug")
    public String mappingHeader(){
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 미디어 타입 조건 매핑 consumes
     * - Content-Type 헤더를 기반으로 매핑
     * - 매핑이 일치하지 않으면 415(Unsupported Media Type) 코드 반환
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes(){
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * 미디어 타입 조건 매핑 produces
     * - Accept 헤더를 기반으로 매핑
     * - 매핑이 일치하지 않으면 406(Not Acceptable) 코드 반환
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */
    @PostMapping(value = "/mapping-produces", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduces(){
        log.info("mappingProduces");
        return "ok";
    }

}
