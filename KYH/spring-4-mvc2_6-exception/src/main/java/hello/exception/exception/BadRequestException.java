package hello.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "잘못된 요청 오류") // 500 -> 400
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "error.bad") // message의 코드화 가능(messages.properties)
public class BadRequestException extends RuntimeException {
    //ResponseStatusExceptionResolver에서 처리 (우선순위2)
}
