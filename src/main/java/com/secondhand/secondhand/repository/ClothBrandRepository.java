package com.secondhand.secondhand.repository;

import com.secondhand.secondhand.model.entity.ClothBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothBrandRepository extends JpaRepository<ClothBrand,Long> {
}
