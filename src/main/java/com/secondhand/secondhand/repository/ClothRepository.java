package com.secondhand.secondhand.repository;

import com.secondhand.secondhand.model.entity.ClothEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothRepository extends JpaRepository<ClothEntity,Long> {
}
