package com.vektorel.restful.repository;

import com.vektorel.restful.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post,Long> {

    List<Post> findByOwnerId(Long ownerId);
}
