package com.vektorel.restful.controller;

import com.vektorel.restful.dto.request.GetOwnerByIdRequestDto;
import com.vektorel.restful.dto.request.LoginRequestDto;
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
    @PostMapping("/save")
    public void save(@RequestBody SaveOwnerRequestDto dto){
        ownerService.save(dto);
    }
    /*
    2_ token talebi
     */
    @GetMapping("/getAll/{token}")
    public ResponseEntity<List<GetAllOwnerResponseDto>> getOwner(@PathVariable String token,@RequestParam String asd){
        return ResponseEntity.ok(ownerService.getAll(token));
    }

    public void deneme(){}
    @PostMapping("/getownerbyid")
    public ResponseEntity<OwnerResponseDto> getOwnerById(GetOwnerByIdRequestDto dto){
        return ResponseEntity.ok(ownerService.findById(dto));
    }

    @PostMapping("/login")
    @Operation(summary = "giris yapılır buradan")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto){
        return ResponseEntity.ok(ownerService.login(dto));
    }

    public void deneme2(){}











}
