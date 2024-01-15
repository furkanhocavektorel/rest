package com.vektorel.restful.service;

import com.vektorel.restful.converter.OwnerConverter;
import com.vektorel.restful.dto.request.GetOwnerByIdRequestDto;
import com.vektorel.restful.dto.request.LoginRequestDto;
import com.vektorel.restful.dto.request.SaveOwnerRequestDto;
import com.vektorel.restful.dto.response.GetAllOwnerResponseDto;
import com.vektorel.restful.dto.response.LoginResponseDto;
import com.vektorel.restful.dto.response.OwnerResponseDto;
import com.vektorel.restful.entity.Owner;
import com.vektorel.restful.exception.custom.*;
import com.vektorel.restful.repository.IOwnerRepository;
import com.vektorel.restful.util.JsonTokenManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerService  {
    private  final IOwnerRepository repository;
    private final PostService postService;
    private final JsonTokenManager jsonTokenManager;
    public final OwnerConverter ownerConverter;

    public OwnerService(IOwnerRepository repository, PostService postService, JsonTokenManager jsonTokenManager, OwnerConverter ownerConverter){
        this.repository=repository;
        this.postService = postService;
        this.jsonTokenManager = jsonTokenManager;
        this.ownerConverter = ownerConverter;
    }
    public void save  (SaveOwnerRequestDto dto){

        if (repository.existsByEmail(dto.getEmail())){
            throw new EmailAlreadyExistsException("kullanici maili kayitli");
        }


        Owner owner=ownerConverter.toOwner(dto);

    repository.save(owner);


    }

    public List<GetAllOwnerResponseDto> getAll(String token) {
        if (token.isBlank()) throw new TokenNotEmptyException();

       Optional<Long> ownerId= jsonTokenManager.getIdByToken(token);

       if (ownerId==null) throw new WrongTokenException();



        List<GetAllOwnerResponseDto> responseDtos = new ArrayList<>();
        for (Owner o: repository.findAll()){

            GetAllOwnerResponseDto responseDto= GetAllOwnerResponseDto.builder()
                            .email(o.getEmail())
                            .surname(o.getSurname())
                            .name(o.getName())
                            .message("ok")
                            .statusCode(200)
                            .build();

            responseDtos.add(responseDto);
        }




        return responseDtos;
    }



    public OwnerResponseDto findById(GetOwnerByIdRequestDto dto) {
        Optional<Owner> owner=repository.findById(dto.getId());

        if (owner.isEmpty()) throw new OwnerNotFoundException();


        return OwnerResponseDto.builder()
                .message("ok")
                .email(owner.get().getEmail())
                .statusCode("200")
                .name(owner.get().getName())
                .surname(owner.get().getSurname())
                .build();

    }





    public LoginResponseDto login(LoginRequestDto dto) {

        if (!repository.existsByEmail(dto.getEmail())){
            throw new EmailLoginException("email kayitli degil");
        }

        Optional<Owner> owner= repository.findOptionalByEmail(dto.getEmail());

        if (!owner.get().getPassword().equals(dto.getPassword())){
            throw new PasswordLoginException("password Not MAtch!!!");
        }


        Optional<String> token=jsonTokenManager.createToken(owner.get().getId());

        return LoginResponseDto.builder()
                .message("login yapıldı afrein sana ")
                .statusCode(2001)
                .token(token.get())
                .build();

    }
}
