package com.secondhand.secondhand.repository;

import com.secondhand.secondhand.model.entity.SpeedyCityEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpeedyCityRepository extends JpaRepository<SpeedyCityEntity,Long> {

//    @Query("select s FROM SpeedyCityEntity s where s.name like :word%")
//    List<SpeedyCityEntity> findAllByCitiesBtStringLike(@Param("word") String wordLike);

    @EntityGraph(value = "country")
    @Query("SELECT c FROM SpeedyCityEntity c where c.name like :word%")
    List<SpeedyCityEntity> findSpeedyCityByStringGraphCountry(@Param("word") String word);

    @EntityGraph(value = "addresses")
    @Query("select a FROM SpeedyCityEntity a where a.id = :cityId")
    Optional<SpeedyCityEntity> findSpeedyAddressByCityIdGraphAddress(@Param("cityId") Long cityId);
}
