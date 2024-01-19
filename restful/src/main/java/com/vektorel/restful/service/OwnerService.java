package com.vektorel.restful.service;

import com.vektorel.restful.dto.request.GetOwnerByIdRequestDto;
import com.vektorel.restful.dto.request.LoginRequestDto;
import com.vektorel.restful.dto.request.OwnerUpdateRequestDto;
import com.vektorel.restful.dto.request.SaveOwnerRequestDto;
import com.vektorel.restful.dto.response.BaseResponseDto;
import com.vektorel.restful.dto.response.GetAllOwnerResponseDto;
import com.vektorel.restful.dto.response.LoginResponseDto;
import com.vektorel.restful.dto.response.OwnerResponseDto;
import com.vektorel.restful.entity.Owner;
import com.vektorel.restful.entity.enums.Role;
import com.vektorel.restful.exception.custom.*;
import com.vektorel.restful.repository.IOwnerRepository;
import com.vektorel.restful.util.JsonTokenManager;
import com.vektorel.restful.util.ServiceManager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerService extends ServiceManager<Owner,Long> {
    private  final IOwnerRepository repository;
    private final PostService postService;
    private final JsonTokenManager jsonTokenManager;

    public OwnerService(IOwnerRepository repository, PostService postService, JsonTokenManager jsonTokenManager){
        super(repository);
        this.repository=repository;
        this.postService = postService;
        this.jsonTokenManager = jsonTokenManager;
    }
    public void ownerSave (SaveOwnerRequestDto dto){
        if (repository.existsByEmail(dto.getEmail())){
            throw new EmailAlreadyExistsException("kullanici maili kayitli");
        }
        Owner owner=Owner.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(Role.MANAGER)
                .build();

    save(owner);


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

    public BaseResponseDto updateOwner(OwnerUpdateRequestDto dto) {
        Optional<Long> id=jsonTokenManager.getIdByToken(dto.getToken());
        if (id==null) throw new TokenNotEmptyException();

        Owner owner=findById(id.get()).orElseThrow(()-> new OwnerNotFoundException());



        owner.setName(dto.getName());
        owner.setEmail(dto.getEmail());
        owner.setSurname(dto.getSurname());

        update(owner);
        return BaseResponseDto.builder()
                .message("ok")
                .statusCode(200)
                .build();
    }

    public BaseResponseDto deleteOwner(String token, long id) {
        // silecek kişi. yetkili kişi.
        Long yoneticiId=jsonTokenManager.getIdByToken(token).orElseThrow(()-> new WrongTokenException());
        Owner owner=findById(yoneticiId).orElseThrow(()->new OwnerNotFoundException());
        if (!owner.getRole().equals(Role.MANAGER)) throw new OwnerNotFoundException();

        // silinecek kişi
        deleteById(id);


        return BaseResponseDto.builder().message("0").statusCode(200).build();

    }
}
