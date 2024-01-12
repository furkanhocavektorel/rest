package com.vektorel.restful.exception;

import com.vektorel.restful.exception.custom.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> emailAlreadyExists(EmailAlreadyExistsException emailEx){

        return new ResponseEntity<>(
                createMessage(ErrorType.EMAIL_ALREADY_EXISTS)
                ,HttpStatus.BAD_GATEWAY);
    }



    @ExceptionHandler(EmailLoginException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage > emailLoginException(EmailLoginException loginException){

        return new ResponseEntity<>(
                createMessage(ErrorType.EMAIL_ALREADY_EXISTS)
                ,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(PasswordLoginException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> passwordLoginException(PasswordLoginException passEx){
        return new ResponseEntity<>(
                createMessage(ErrorType.PASSWORD_NOT_MATCH)
                ,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OwnerNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> ownerNotFoundException(OwnerNotFoundException ex){
        return new ResponseEntity<>(
                createMessage(ErrorType.OWNER_NOT_FOUND_EXCEPTION)
                ,HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(TokenNotEmptyException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> tokenNotEmpty(TokenNotEmptyException ex){
        return new ResponseEntity<>(
                createMessage(ErrorType.TOKEN_NOT_EMPTY)
                ,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongTokenException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> wrongTokenException(WrongTokenException ex){

        return new ResponseEntity<>(
                createMessage(ErrorType.WROND_TOKEN)
                ,HttpStatus.BAD_REQUEST);
    }










    public ErrorMessage createMessage(ErrorType errorType){
        return ErrorMessage.builder()
                .message(errorType.message)
                .statusCode(errorType.statusCode)
                .build();
    }


}
