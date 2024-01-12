package com.vektorel.restful.exception;

import lombok.Builder;

@Builder
public class ErrorMessage {
     public int statusCode;
     public String message;
}
