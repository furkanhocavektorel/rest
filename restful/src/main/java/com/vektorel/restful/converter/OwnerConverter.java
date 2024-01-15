package com.vektorel.restful.converter;

import com.vektorel.restful.dto.request.SaveOwnerRequestDto;
import com.vektorel.restful.entity.Owner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class OwnerConverter {

    public Owner toOwner(SaveOwnerRequestDto dto){
        Owner owner=Owner.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
        return owner;
    }

}
