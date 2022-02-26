package com.secondhand.secondhand.model.entity;

import com.secondhand.secondhand.model.entity.enums.ClothColorEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSeasonEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSexEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSizeEnum;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "clothes")
public class ClothEntity extends BaseEntity{


    @ManyToOne
    private ClothTypeEntity clothType;

    @ManyToOne
    private ClothBrand clothBrand;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClothSizeEnum clothSize;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClothSexEnum clothSex;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClothColorEnum clothColor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClothSeasonEnum clothSeason;

    @ManyToOne
    private ClothCompositionEntity clothComposition;

    @Column(nullable = false)
    @Size(max = 50)
    @NotBlank
    private String description;

    @Column(nullable = false)
    @Positive
    @NotEmpty
    private Long startPrice;

    @Column(nullable = false)
    @Positive
    @NotEmpty
    private Long newPrice;

    @Column(nullable = false)
    @PositiveOrZero
    @NotEmpty
    private Integer clothCount;

}
