package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller //스프링이 자동으로 빈으로 등록한다(내부에 @Component), 스프링 MVC 에서 어노테이션 기반 컨트롤러로 인식
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form") //요청 정보를 매핑한다. 해당 URL 이 호출되면 이 메서드가 실행
    public ModelAndView process(){
        return new ModelAndView("new-form");
    }

}
