package nig.ger.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String ROOT_REDIRECT = "redirect:/";

    @ExceptionHandler(Throwable.class)
    public String handleThrowable(Throwable throwable) {
        throwable.printStackTrace();
        return ROOT_REDIRECT;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle404(NoHandlerFoundException e) {
        e.printStackTrace();
        return ROOT_REDIRECT;
    }
}
