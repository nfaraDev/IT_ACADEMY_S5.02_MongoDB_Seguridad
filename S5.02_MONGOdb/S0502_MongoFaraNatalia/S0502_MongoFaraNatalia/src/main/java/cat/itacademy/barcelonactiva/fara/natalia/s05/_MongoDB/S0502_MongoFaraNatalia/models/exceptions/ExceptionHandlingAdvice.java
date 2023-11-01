package cat.itacademy.barcelonactiva.fara.natalia.s05._MongoDB.S0502_MongoFaraNatalia.models.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlingAdvice {

    @ResponseBody
    @ExceptionHandler({ElementNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    MessageError  notFoundHandler(Exception ex) {
        return new MessageError (ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ResponseBody
    @ExceptionHandler({ElementAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    MessageError  badRequestHandler1(Exception ex) {
        return new MessageError (ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    }
    @ResponseBody
    @ExceptionHandler({ElementsDoesntEqualsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    MessageError notFoundHandler2(Exception ex) {
        if (ex == null) {
            return new MessageError("Mensaje por defecto", HttpStatus.BAD_REQUEST.value());
        }
        return new MessageError(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    public class MessageError {
        private String message;
        private int status;
        private LocalDateTime time;

        public MessageError(String message, int status) {
            this.message = message;
            this.status = status;
            this.time = LocalDateTime.now();
        }
    }

}