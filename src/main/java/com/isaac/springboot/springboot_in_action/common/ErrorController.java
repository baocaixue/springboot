package com.isaac.springboot.springboot_in_action.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ErrorController extends AbstractErrorController {
    @Autowired
    ObjectMapper objectMapper;

    public ErrorController() {
        super(new DefaultErrorAttributes());
    }

    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    public ErrorController(ErrorAttributes errorAttributes, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorViewResolvers);
    }


    @RequestMapping("/error")
    public ModelAndView getErrorPath(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(req, false));
        //获取异常有可能为空
        Throwable cause = getCause(req);
        int status = (Integer)model.get("status");
        String message = (String)model.get("message");
        String errorMessage = getErrorMessage(cause);
        resp.setStatus(status);
        if (!isJsonRequest(req)) {
            ModelAndView view = new ModelAndView("/error.html");
            view.addObject(model);
            view.addObject("errorMessage",errorMessage);
            view.addObject("status",status);
            view.addObject("cause",cause);
            return view;
        } else {
            Map error = new HashMap();
            error.put("success",false);
            error.put("errorMessage",errorMessage);
            error.put("message",message);
            //writeJson(response,error);
            return null;
        }
    }

    private boolean isJsonRequest(HttpServletRequest req) {
        String requestUri = (String)req.getAttribute("javax.servlet.error.request_uri");
        if (requestUri != null && requestUri.endsWith(".json")) {
            return true;
        } else {
            //req.getHeader("Accept").contains("application/json")
            return false;
        }
    }

    private String getErrorMessage(Throwable cause) {
        return "服务器错误，请联系管理员";
    }

    private Throwable getCause(HttpServletRequest req) {
        Throwable error = (Throwable) req.getAttribute("javax.servlet.error.exception");
        if (error != null) {
            while (error instanceof ServletException && error.getCause() != null) {
                error = ((ServletException) error).getCause();
            }
        }
        return error;
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
