package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    //오류발생 이유
    /*
        request 스코프를 내놓을려고 하는데 request가 존재하지도 않다.
        스코프 request가 활성화 되지가 않은 뜻이다.

        해결 방법: provider를 사용하면 된다.

     */

    //자동으로 의존관계 주입
    private final LogDemoService logDemoService;
    //request 스코프 방식
    //private final ObjectProvider<MyLogger> myLoggerProvider;
    //프록시 스코프 방식
    private final MyLogger myLogger;


    @RequestMapping("log-demo") //log-demo라는 요청이 오면
    @ResponseBody //뷰 화면이 없이 바로 반환을 받고 싶을 때
    //자바에서 제공하는 표준 서블릿 규약이 있는데 그것에 의한 요청을 받을 수 있다.
    public String logDemo(HttpServletRequest request) throws InterruptedException{
        //고객이 어떤 url로 요청을 해왔는지 알 수 있다.
        String requestURL = request.getRequestURL().toString();

        //실제 컨트롤러 요청이 들어와서 http가 살아있다는 뜻이다.
        //살아있는 상태에서 스코프를 사용할 수 있는 상황이 되고 꺼내 사용이 가능하다.
        //MyLogger myLogger = myLoggerProvider.getObject();

        //스프링 조작해서 만든 애가 지금 스프링 빈으로 등록되는 것을 확인할 수 있다.
        System.out.println("mylogger = " + myLogger.getClass());

        //url 정보를 둔다.
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testID");

        return "OK";
    }

}
