package com.vektorel.restful.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaveOwnerRequestDto {
   private String name;
    private String surname;
    private String email;
    private String password;
}
