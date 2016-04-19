package spittr.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/** @author Yuriy */
@ControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler(DuplicateSpittleException.class)
    public String duplicateExceptionHandler(){
        return "error/duplicate";
    }
}
