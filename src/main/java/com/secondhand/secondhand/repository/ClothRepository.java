package com.secondhand.secondhand.repository;

import com.secondhand.secondhand.model.entity.ClothEntity;
import com.secondhand.secondhand.model.entity.enums.ClothColorEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSexEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSizeEnum;
import com.secondhand.secondhand.model.entity.enums.ItemTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClothRepository extends JpaRepository<ClothEntity, Long> {

    //    @Query("SELECT c FROM Customer c WHERE (:name is null or c.name = :name) and (:email is null"
//            + " or c.email = :email)")
//    List<Customer> findCustomerByNameAndEmail(@Param("name") String name, @Param("email") String email);
    @Query("SELECT c FROM ClothEntity  c WHERE (:brand is null or c.clothBrandEntity.name = :brand) " +
            "and (:size is null or c.clothSize = :size) " +
            "and (:discount is null or ((c.startPrice - c.newPrice)/c.startPrice)* 100 <= :discount) " +
            "and (:discountStart is null or ((c.startPrice - c.newPrice)/c.startPrice)* 100 >= :discountStart) " +
            "and (:color is null or c.clothColor = :color) " +
            "and (:priceLow is null and :priceHigh is null or (c.newPrice >= :priceLow and c.newPrice <= :priceHigh))" +
            "and (:sex is null or c.clothSex = :sex)" +
            "and (coalesce(:type,null) is null or c.clothType.name in (:type))" +
            "and (:itemType is null or c.clothType.type = :itemType)")
    Page<ClothEntity> getAllClothesByParamsOr(@Param("brand") String brand,
                                              @Param("size") ClothSizeEnum size,
                                              @Param("discount") Long discount,
                                              @Param("discountStart") Long discountStart,
                                              @Param("color") ClothColorEnum color,
                                              @Param("priceLow") Long priceLow,
                                              @Param("priceHigh") Long priceHigh,
                                              @Param("sex") ClothSexEnum sex,
                                              @Param("type") List<String> type,
                                              @Param("itemType") ItemTypeEnum itemTypeEnum,
                                              Pageable pageable);

    @Query("SELECT c FROM ClothEntity  c WHERE (coalesce(:sex,null) is null or c.clothSex in (:sex))" +
            "and (:brand is null or c.clothBrandEntity.name = :brand)" +
            "and (:size is null or c.clothSize = :size)" +
            "and (:color is null or c.clothColor = :color)" +
            "and (:discount is null or ((c.startPrice - c.newPrice)/c.startPrice)* 100 <= :discount) " +
            "and (:discountStart is null or ((c.startPrice - c.newPrice)/c.startPrice)* 100 >= :discountStart) " +
            "and (:itemType is null or c.clothType.type = :itemType)")
    Page<ClothEntity> getAllClothesByList(@Param("sex") List<ClothSexEnum> sex,
                                          @Param("brand") String brand,
                                          @Param("size") ClothSizeEnum size,
                                          @Param("color") ClothColorEnum color,
                                          @Param("discount") Long discount,
                                          @Param("discountStart") Long discountStart,
                                          @Param("itemType") ItemTypeEnum itemType,
                                          Pageable pageable);
}
