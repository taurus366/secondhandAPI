package com.secondhand.secondhand.repository;

import com.secondhand.secondhand.model.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity,Long> {

    RegionEntity findByName(String name);
}
