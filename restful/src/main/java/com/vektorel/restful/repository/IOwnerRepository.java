package com.vektorel.restful.repository;

import com.vektorel.restful.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetTime;
import java.util.Optional;

@Repository
public interface IOwnerRepository extends JpaRepository<Owner,Long> {
    Boolean existsByEmail(String email);
    Optional<Owner> findOptionalByEmail(String email);

}
