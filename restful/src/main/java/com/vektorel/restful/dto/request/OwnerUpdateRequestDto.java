package com.vektorel.restful.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OwnerUpdateRequestDto {
    private String token;
    private String name;
    private String surname;
    private String email;
}
