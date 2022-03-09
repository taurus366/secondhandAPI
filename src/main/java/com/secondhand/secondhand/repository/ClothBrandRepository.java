package com.secondhand.secondhand.repository;

import com.secondhand.secondhand.model.entity.ClothBrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothBrandRepository extends JpaRepository<ClothBrandEntity,Long> {

    ClothBrandEntity findByName(String brandName);
}
