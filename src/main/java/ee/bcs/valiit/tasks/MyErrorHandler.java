package ee.bcs.valiit.tasks;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MyException.class)
    public ResponseEntity<ErrorResponse> handleRunError(MyException ex) {
        System.out.println("handle exception here 2");
        ErrorResponse response2 = new ErrorResponse();
        response2.setMessage(ex.getMessage());
        //response2.setMessage("Midagi läks jooksuga pekki");
        //return new ResponseEntity<>(new ErrorResponse(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response2, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    /*
        @ExceptionHandler(NullPointerException.class)
        public ResponseEntity<ErrorResponse> handleNullError(NullPointerException ex) {
            System.out.println("handle exception here Null");
            ErrorResponse response3 = new ErrorResponse();
            //response2.setMessage(ex.getMessage());
            response3.setMessage("Midagi läks mitme nulliga pekki");
            //return new ResponseEntity<>(new ErrorResponse(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response3, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(RuntimeException.class)
        public ResponseEntity<ErrorResponse> handleRunError(RuntimeException ex) {
            System.out.println("handle exception here 2");
            ErrorResponse response2 = new ErrorResponse();
            response2.setMessage(ex.getMessage());
            //response2.setMessage("Midagi läks jooksuga pekki");
            //return new ResponseEntity<>(new ErrorResponse(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response2, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
*/
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleError(Exception ex) {
        System.out.println("handle exception here");
        ex.printStackTrace();
        ErrorResponse response = new ErrorResponse();
        response.setMessage(ex.getMessage());
        //response.setMessage("Midagi läks pekki");
        return new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
