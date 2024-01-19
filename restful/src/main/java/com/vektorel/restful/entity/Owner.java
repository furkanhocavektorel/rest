package com.vektorel.restful.entity;

import com.vektorel.restful.entity.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Owner extends BaseEntity, UserDetail{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long adres_id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Enum gender;
    private String phoneNumber;
    private String tc;
    // role enum tutulur
    private Role role;
}
