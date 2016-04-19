package spittr.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** @author Yuriy */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Spittle not found")
public class SpittleNotFoundException extends RuntimeException{

}
