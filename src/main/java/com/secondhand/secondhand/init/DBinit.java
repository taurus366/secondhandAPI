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

            roleRepository.saveAll(List.of(userRole,adminRole));
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

            this.clothBrandRepository.saveAll(List.of(brandEntity,brandEntity2,brandEntity3,brandEntity4));
        }
    }

    private void initTypes() {
        if (this.clothTypeRepository.findAll().size() == 0) {

            ClothTypeEntity clothType = new ClothTypeEntity();
            clothType
                    .setName("PULLOVER")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType2 = new ClothTypeEntity();
            clothType2
                    .setName("DRESS")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType3 = new ClothTypeEntity();
            clothType3
                    .setName("T-SHORT")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType4 = new ClothTypeEntity();
            clothType4
                    .setName("JEANS")
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            this.clothTypeRepository.saveAll(List.of(clothType,clothType2,clothType3,clothType4));
        }
    }

    private void initCompositions() {

        if (this.clothCompositionRepository.findAll().size() == 0){

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
                    .saveAll(List.of(clothComposition,clothComposition2,clothComposition3,clothComposition4,clothComposition5));
        }
    }
}
