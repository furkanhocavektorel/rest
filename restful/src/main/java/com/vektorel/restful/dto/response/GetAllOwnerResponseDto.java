package com.vektorel.restful.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllOwnerResponseDto {
    String name;
    String surname;
    String email;
    String message;
    String statusCode;

}
