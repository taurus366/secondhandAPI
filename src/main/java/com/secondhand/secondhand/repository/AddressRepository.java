package com.secondhand.secondhand.repository;

import com.secondhand.secondhand.model.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity,Long> {

//    List<AddressEntity> findAllByUserId(Long userId);
}
