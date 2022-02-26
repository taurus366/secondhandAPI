package com.secondhand.secondhand.repository;

import com.secondhand.secondhand.model.entity.ClothCompositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothCompositionRepository extends JpaRepository<ClothCompositionEntity,Long> {
}
