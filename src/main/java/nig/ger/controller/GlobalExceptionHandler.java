package nig.ger.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import static nig.ger.util.Constants.ROOT_REDIRECT;

@Slf4j
@Controller
@ControllerAdvice
public class GlobalExceptionHandler implements ErrorController {
    @RequestMapping("/error")
    public String handle(HttpServletRequest request) throws Throwable {
        Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

        if ((int) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE) == 404) {
            log.error("No mapping found for request: " + request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI));
        } else if (throwable != null) {
            throw throwable;
        }

        return ROOT_REDIRECT;
    }

    @ExceptionHandler(Throwable.class)
    public String handleThrowable(Throwable throwable) {
        throwable.printStackTrace();
        return ROOT_REDIRECT;
    }
}
