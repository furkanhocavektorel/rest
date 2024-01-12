package com.vektorel.restful.service;

import com.vektorel.restful.dto.request.PostSaveRequestDto;
import com.vektorel.restful.dto.response.BaseResponseDto;
import com.vektorel.restful.dto.response.PostResponseDto;
import com.vektorel.restful.entity.Post;
import com.vektorel.restful.repository.IPostRepository;
import com.vektorel.restful.util.JsonTokenManager;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final IPostRepository repository;
    private final JsonTokenManager jsonTokenManager;

    public PostService(IPostRepository repository, JsonTokenManager jsonTokenManager) {
        this.repository = repository;
        this.jsonTokenManager = jsonTokenManager;
    }

    public BaseResponseDto save(PostSaveRequestDto dto){
        Optional<Long> id=jsonTokenManager.getIdByToken(dto.getToken());

        if (id.isEmpty()){
            throw new RuntimeException();
        }

        Post post= Post.builder()
                .likeCount(0)
                .dislikeCount(0)
                .sharedTime(LocalDateTime.now().toString())
                .description(dto.getDescription())
                .title(dto.getTitle())
                .ownerId(id.get())
                .build();

        repository.save(post);


        return BaseResponseDto.builder()
                .message("ok")
                .statusCode(200)
                .build();
    }

    public List<PostResponseDto> getAllPostByOwnerId(Long ownerId){
        List<Post> posts= repository.findByOwnerId(ownerId);
        List<PostResponseDto> responseDtos=new ArrayList<>();
        PostResponseDto postResponseDto= new PostResponseDto();

        for (Post asdasd: posts){
            responseDtos.add(PostResponseDto.builder()
                            .description(asdasd.getDescription())
                            .title(asdasd.getTitle())
                            .imageUrl(asdasd.getImageUrl())
                            .sharedTime(asdasd.getSharedTime())
                    .build());
        }
        return responseDtos;
    }









    public List<Post> getAllPost(){
        return repository.findAll();
    }



}
