package com.vektorel.restful.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponseDto {
    private Long ownerinIdsiBuymus;
    private String title;
    private String description;
    private String sharedTime;
    private String imageUrl;
}
