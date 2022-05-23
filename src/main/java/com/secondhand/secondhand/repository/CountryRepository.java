package com.secondhand.secondhand.repository;

import com.secondhand.secondhand.model.entity.CountryEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Long> {

    CountryEntity findByName(String name);

    @EntityGraph(value = "speedy")
    @Query("select c from  CountryEntity c where c.name = :name")
    CountryEntity getCountryGraphSpeedy(@Param("name") String name);
}
