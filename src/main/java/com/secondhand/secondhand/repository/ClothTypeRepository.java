package com.secondhand.secondhand.repository;

import com.secondhand.secondhand.model.entity.ClothTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothTypeRepository extends JpaRepository<ClothTypeEntity,Long> {
}
