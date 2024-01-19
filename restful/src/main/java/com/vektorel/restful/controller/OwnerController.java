package com.vektorel.restful.controller;

import com.vektorel.restful.dto.request.GetOwnerByIdRequestDto;
import com.vektorel.restful.dto.request.LoginRequestDto;
import com.vektorel.restful.dto.request.OwnerUpdateRequestDto;
import com.vektorel.restful.dto.request.SaveOwnerRequestDto;
import com.vektorel.restful.dto.response.BaseResponseDto;
import com.vektorel.restful.dto.response.GetAllOwnerResponseDto;
import com.vektorel.restful.dto.response.LoginResponseDto;
import com.vektorel.restful.dto.response.OwnerResponseDto;
import com.vektorel.restful.entity.Owner;
import com.vektorel.restful.service.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
@AllArgsConstructor
public class OwnerController {
    @Autowired
    private OwnerService ownerService;
    @PostMapping("")
    @PutMapping
    @DeleteMapping
    public void save(@RequestBody SaveOwnerRequestDto dto){
        ownerService.ownerSave(dto);
    }
    /*
    2_ token talebi
     */
    @GetMapping("/{token}")
    public ResponseEntity<List<GetAllOwnerResponseDto>> getOwner(@PathVariable String token,@RequestParam String asd){
        return ResponseEntity.ok(ownerService.getAll(token));
    }

    public void deneme(){}
    @PostMapping("/getownerbyid")
    @PreAuthorize("ADMIN")
    public ResponseEntity<OwnerResponseDto> getOwnerById(GetOwnerByIdRequestDto dto){
        return ResponseEntity.ok(ownerService.findById(dto));
    }

    @PostMapping("/login")
    @Operation(summary = "giris yapılır buradan")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto){
        return ResponseEntity.ok(ownerService.login(dto));
    }

    public void deneme2(){}

    @PutMapping("/")
    public ResponseEntity<BaseResponseDto> updateOwner(@RequestBody OwnerUpdateRequestDto dto){

        return ResponseEntity.ok(ownerService.updateOwner(dto));
    }

    @GetMapping("/")
    public ResponseEntity<List<Owner>> getAll(){
        return ResponseEntity.ok(ownerService.getAll());
    }

    @DeleteMapping("/{a}/{silinecekid}")
    public ResponseEntity<BaseResponseDto> delete(@PathVariable("a") String token
            ,@PathVariable("silinecekid") long id){
        return ResponseEntity.ok(ownerService.deleteOwner(token,id));
    }











}
