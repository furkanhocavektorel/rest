package com.vektorel.restful.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OwnerResponseDto {
    String name;
    String surname;
    String email;
    String message;
    String statusCode;
}
