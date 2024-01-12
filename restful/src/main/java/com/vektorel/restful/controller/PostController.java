package com.vektorel.restful.controller;

import com.vektorel.restful.dto.request.PostSaveRequestDto;
import com.vektorel.restful.dto.response.BaseResponseDto;
import com.vektorel.restful.dto.response.PostResponseDto;
import com.vektorel.restful.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/save")
    public ResponseEntity<BaseResponseDto> save(@RequestBody PostSaveRequestDto dto){
        return ResponseEntity.ok(postService.save(dto));
    }


    @GetMapping("/getpostbyownerId")
    public ResponseEntity<List<PostResponseDto>> getPostByOwnerId(@RequestParam Long ownerId){
        return ResponseEntity.ok(postService.getAllPostByOwnerId(ownerId));
    }
}
