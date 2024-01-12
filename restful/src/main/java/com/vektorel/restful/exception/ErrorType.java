package com.vektorel.restful.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorType {
    // EXISTS
    EMAIL_ALREADY_EXISTS(15001,"Email already exists"),

    // NOT MATCH
    PASSWORD_NOT_MATCH(15003,"Password not match"),

    // NOT FOUND
    TOKEN_NOT_EMPTY(16001,"Token not empty"),
    OWNER_NOT_FOUND_EXCEPTION(15002,"Owner Not Found"),


    // WRONG
    WROND_TOKEN(16002,"Wrong token");







    int statusCode;
    String message;

}
