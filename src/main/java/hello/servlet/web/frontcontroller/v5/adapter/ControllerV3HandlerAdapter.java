package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV3 controller = (ControllerV3) handler; // V3 컨트롤러가 입력인자로 들어옴

        Map<String, String> paramMap = createParamMap(request); // key에 username과 age를 파라미터값을 value에 넣기
        ModelView mv = controller.process(paramMap); // 해당 컨트롤러에 파라미터가 저장된 Map을 입력인자로 넣어 viewName과, username과 age가 저장된 ModelView반환

        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        //paramMap
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName))); // username과 age를 key에, 파라미터값을 value에 넣기
        return paramMap;
    }
}
