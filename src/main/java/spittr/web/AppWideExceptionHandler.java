package spittr.web;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/** @author Yuriy */
@ControllerAdvice
public class AppWideExceptionHandler {
    private static Logger log = LoggerFactory.getLogger(AppWideExceptionHandler.class);
    
    @ExceptionHandler(DuplicateSpittleException.class)
    public ModelAndView duplicateExceptionHandler(HttpServletRequest request, Exception e){
        log.error("Request: {} raised {}", request.getRequestURL(), e);
        
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("error/duplicate");
        
        return mav;
    }
}
