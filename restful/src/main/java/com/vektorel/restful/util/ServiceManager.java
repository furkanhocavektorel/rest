package com.vektorel.restful.util;

import com.vektorel.restful.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ServiceManager<T extends BaseEntity,ID> implements IServiceManager<T,ID> {

    private final JpaRepository<T,ID> jpaRepository;

    public ServiceManager(JpaRepository<T, ID> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(T t) {
        t.setCrateDate(LocalDateTime.now().toString());
        t.setUpdateDate(LocalDateTime.now().toString());
        t.setStatus(0);
        jpaRepository.save(t);
    }

    @Override
    public void deleteById(ID id) {
        jpaRepository.deleteById(id);

    }

    @Override
    public void delete(T t) {
        t.setStatus(2);
        update(t);
    }

    @Override
    public void update(T t) {
        t.setUpdateDate(LocalDateTime.now().toString());
        jpaRepository.save(t);
    }

    @Override
    public Optional<T> findById(ID id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<T> getAll() {
        return jpaRepository.findAll();
    }
}
