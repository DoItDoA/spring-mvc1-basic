package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler; // V4 컨트롤러가 입력인자로 들어옴

        Map<String, String> paramMap = createParamMap(request); // key에 username과 age를 파라미터값을 value에 넣기
        Map<String, Object> model = new HashMap<>(); // member나 members를 key와 value로 저장되는 Map 생성

        String viewName = controller.process(paramMap, model); // 해당 컨트롤러에 파라미터가 저장된 Map과 member(s)를 담게하는 Map을 입력인자로 넣어 ViewName 반환

        ModelView mv = new ModelView(viewName); // viewName을 ModelView에 담기
        mv.setModel(model); // member(s)를 ModelView에 담기

        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        //paramMap
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
