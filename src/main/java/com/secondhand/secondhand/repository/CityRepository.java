package com.secondhand.secondhand.repository;

import com.secondhand.secondhand.model.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {

    @Query("select c FROM CityEntity c where c.city like :word%")
    List<CityEntity> findAllByCitiesBtStringLike(@Param("word") String wordLike);
}
