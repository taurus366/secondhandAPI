package com.secondhand.secondhand.init;

import com.secondhand.secondhand.model.entity.*;
import com.secondhand.secondhand.model.entity.enums.RoleEnum;
import com.secondhand.secondhand.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class DBinit implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final ClothBrandRepository clothBrandRepository;
    private final ClothTypeRepository clothTypeRepository;
    private final ClothCompositionRepository clothCompositionRepository;
    private final UserRepository userRepository;


    public DBinit(RoleRepository roleRepository, ClothBrandRepository clothBrandRepository, ClothTypeRepository clothTypeRepository, ClothCompositionRepository clothCompositionRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.clothBrandRepository = clothBrandRepository;
        this.clothTypeRepository = clothTypeRepository;
        this.clothCompositionRepository = clothCompositionRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        initUserRoles();
        initBrands();
        initTypes();
        initCompositions();
//        initUser();
    }

//    private void initUser() {
//        RoleEntity byId = roleRepository.getById(1L);
//        RoleEntity byId1 = roleRepository.getById(2L);
//
//        UserEntity user = new UserEntity();
//        user.setCreated(Instant.now())
//                .setModified(Instant.now());
//        user
//                .setActive(true)
//                .getRoles().addAll(List.of(byId,byId1));
//        user
//                .se
//
//
//    }

    private void initUserRoles() {
        List<RoleEntity> roleEntities = roleRepository
                .findAll();

        if (roleEntities.size() == 0) {

            RoleEntity userRole = new RoleEntity();
            userRole.setRole(RoleEnum.USER);

            RoleEntity adminRole = new RoleEntity();
            adminRole.setRole(RoleEnum.ADMINISTRATOR);

            roleRepository.saveAll(List.of(userRole, adminRole));
        }
    }

    private void initBrands() {
        if (this.clothBrandRepository.findAll().size() == 0) {

            ClothBrandEntity brandEntity = new ClothBrandEntity();
            brandEntity
                    .setName("NIKE")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothBrandEntity brandEntity2 = new ClothBrandEntity();
            brandEntity2
                    .setName("ADIDAS")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothBrandEntity brandEntity3 = new ClothBrandEntity();
            brandEntity3
                    .setName("NEW BRAND")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothBrandEntity brandEntity4 = new ClothBrandEntity();
            brandEntity4
                    .setName("OLD BRAND")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            this.clothBrandRepository.saveAll(List.of(brandEntity, brandEntity2, brandEntity3, brandEntity4));
        }
    }

    private void initTypes() {
        if (this.clothTypeRepository.findAll().size() == 0) {

            ClothTypeEntity clothType = new ClothTypeEntity();
            clothType
                    .setName("PULLOVERS")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType2 = new ClothTypeEntity();
            clothType2
                    .setName("DRESSES")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType3 = new ClothTypeEntity();
            clothType3
                    .setName("T-SHORTS")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType4 = new ClothTypeEntity();
            clothType4
                    .setName("JEANS")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType5 = new ClothTypeEntity();
            clothType5
                    .setName("TRACKSUITS")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType6 = new ClothTypeEntity();
            clothType6
                    .setName("SWIMWEARS")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType7 = new ClothTypeEntity();
            clothType7
                    .setName("UNDERWEARS")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType8 = new ClothTypeEntity();
            clothType8
                    .setName("WINTER EQUIPMENTS")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType9 = new ClothTypeEntity();
            clothType9
                    .setName("BLOUSES")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType10 = new ClothTypeEntity();
            clothType10
                    .setName("ELETS")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());


            ClothTypeEntity clothType11 = new ClothTypeEntity();
            clothType11
                    .setName("VESTS")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType12 = new ClothTypeEntity();
            clothType12
                    .setName("WEDGES")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType13 = new ClothTypeEntity();
            clothType13
                    .setName("COSTUMES")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType14 = new ClothTypeEntity();
            clothType14
                    .setName("SHORTS")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType15 = new ClothTypeEntity();
            clothType15
                    .setName("COATS")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType16 = new ClothTypeEntity();
            clothType16
                    .setName("PANTS")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType17 = new ClothTypeEntity();
            clothType17
                    .setName("MOUNTAIN EQUIPMENTS")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType18 = new ClothTypeEntity();
            clothType18
                    .setName("POLY")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType19 = new ClothTypeEntity();
            clothType19
                    .setName("T-SHIRTS")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType20 = new ClothTypeEntity();
            clothType20
                    .setName("SWEATERS")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType21 = new ClothTypeEntity();
            clothType21
                    .setName("SHIRTS")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType22 = new ClothTypeEntity();
            clothType22
                    .setName("SPORTSWEAR")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType23 = new ClothTypeEntity();
            clothType23
                    .setName("TUNICS")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType24 = new ClothTypeEntity();
            clothType24
                    .setName("PANTS")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType25 = new ClothTypeEntity();
            clothType25
                    .setName("BICYCLE EQUIPMENTS")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType26 = new ClothTypeEntity();
            clothType26
                    .setName("FOOTBALL EQUIPMENTS")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());


            this.clothTypeRepository.saveAll(
                    List.of(clothType, clothType2, clothType3, clothType4, clothType5, clothType6, clothType7,
                            clothType8, clothType9, clothType10, clothType11, clothType12, clothType13, clothType14, clothType15, clothType16,
                            clothType17, clothType18, clothType19, clothType20, clothType21, clothType22, clothType23, clothType24, clothType25,
                            clothType26)
            );
        }
    }

    private void initCompositions() {

        if (this.clothCompositionRepository.findAll().size() == 0) {

            ClothCompositionEntity clothComposition = new ClothCompositionEntity();
            clothComposition
                    .setName("NYLON")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothCompositionEntity clothComposition2 = new ClothCompositionEntity();
            clothComposition2
                    .setName("COTTON")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothCompositionEntity clothComposition3 = new ClothCompositionEntity();
            clothComposition3
                    .setName("SILK")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothCompositionEntity clothComposition4 = new ClothCompositionEntity();
            clothComposition4
                    .setName("WOOL")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothCompositionEntity clothComposition5 = new ClothCompositionEntity();
            clothComposition5
                    .setName("LEATHER")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            this.clothCompositionRepository
                    .saveAll(List.of(clothComposition, clothComposition2, clothComposition3, clothComposition4, clothComposition5));
        }
    }
}
