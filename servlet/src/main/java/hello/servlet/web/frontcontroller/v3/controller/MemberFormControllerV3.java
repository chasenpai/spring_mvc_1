package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

    /**
     * 서블릿에 종속적인 코드 제거
     * - Model 객체를 직접 만들어서 사용한다
     * - 구현이 매우 단순해지고 테스트가 쉬워진다
     * - HttpServletRequest 가 제공하는 파라미터는 프론트 컨트롤러가 paramMap 에 담아서 호출
     * - 응답 결과로 뷰 이름과 뷰에 전달할 Model 데이터를 포함하는 ModelView 객체를 반환
     */
    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form"); //view 의 논리적인 이름만 지정
    }

}
