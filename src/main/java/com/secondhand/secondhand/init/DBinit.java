package com.secondhand.secondhand.init;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.secondhand.secondhand.model.dto.CityDTO;
import com.secondhand.secondhand.model.service.JsonCityVillageServiceModel;
import com.secondhand.secondhand.model.entity.*;
import com.secondhand.secondhand.model.entity.enums.ItemTypeEnum;
import com.secondhand.secondhand.model.entity.enums.RoleEnum;
import com.secondhand.secondhand.model.service.JsonCountryServiceModel;
import com.secondhand.secondhand.model.service.JsonRegionServiceModel;
import com.secondhand.secondhand.model.service.JsonSpeedyCityServiceModel;
import com.secondhand.secondhand.repository.*;
import com.secondhand.secondhand.service.CityService;
import com.secondhand.secondhand.service.CountryService;
import com.secondhand.secondhand.service.RegionService;
import com.secondhand.secondhand.service.SpeedyCityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Component
public class DBinit implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final ClothBrandRepository clothBrandRepository;
    private final ClothTypeRepository clothTypeRepository;
    private final ClothCompositionRepository clothCompositionRepository;
    private final CityService cityService;
    private final CountryService countryService;
    private final RegionService regionService;
    private final SpeedyCityService speedyCityService;


    public DBinit(RoleRepository roleRepository, ClothBrandRepository clothBrandRepository, ClothTypeRepository clothTypeRepository, ClothCompositionRepository clothCompositionRepository, CityService cityService, CountryService countryService, RegionService regionService, SpeedyCityService speedyCityService) {
        this.roleRepository = roleRepository;
        this.clothBrandRepository = clothBrandRepository;
        this.clothTypeRepository = clothTypeRepository;
        this.clothCompositionRepository = clothCompositionRepository;
        this.cityService = cityService;
        this.countryService = countryService;
        this.regionService = regionService;
        this.speedyCityService = speedyCityService;
    }


    @Override
    public void run(String... args) {
        initUserRoles();
        initBrands();
        initTypes();
        initCompositions();
        initCityDB();
        initCountriesDB();
        initRegions();
        initSpeedyCityDB();

    }

    private void initRegions() {
       if ( regionService
               .getAllRegions().size() > 0){
           return;
       }

        ObjectMapper mapper = new ObjectMapper();
        Path path = Paths.get("src/main/resources/regions_db.json");

        try {

            List<JsonRegionServiceModel> regions = Arrays.asList(mapper.readValue(path.toFile(), JsonRegionServiceModel[].class));

            this.regionService
                    .postRegions(regions);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initSpeedyCityDB() {

    if (this.speedyCityService.getAllCities().size() > 0){
        return;
    }

        ObjectMapper mapper = new ObjectMapper();
        Path path = Paths.get("src/main/resources/speedy_city_address.json");

        try {

            List<JsonSpeedyCityServiceModel> speedyCityServiceModels = Arrays.asList(mapper.readValue(path.toFile(), JsonSpeedyCityServiceModel[].class));

            this.speedyCityService
                    .postAllCities(speedyCityServiceModels);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initCountriesDB() {
        List<CountryEntity> allCountries = this.countryService
                .getAllCountries();

        if (allCountries.size() > 0) {
            return;
        }

            ObjectMapper mapper = new ObjectMapper();
        Path path = Paths.get("src/main/resources/countries_db.json");

        try {

            List<JsonCountryServiceModel> countries = Arrays.asList(mapper.readValue(path.toFile(), JsonCountryServiceModel[].class));

            this.countryService
                    .postCountries(countries);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initCityDB() {

        List<CityDTO> allCitiesVillages = this.cityService
                .getAllCitiesVillages();

        if (allCitiesVillages.size() > 1){
                return;
        }

        ObjectMapper mapper = new ObjectMapper();
        Path path = Paths.get("src/main/resources/city_village_db.json");

        try {
            List<JsonCityVillageServiceModel> citiesAndVillages = Arrays.asList(mapper.readValue(path.toFile(), JsonCityVillageServiceModel[].class));

            this.cityService
                    .postCitiesVillages(citiesAndVillages);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

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
                    .setGender("MALE/FEMALE/GIRLS/BOYS")
                    .setType(ItemTypeEnum.CLOTH)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType2 = new ClothTypeEntity();
            clothType2
                    .setName("DRESSES")
                    .setGender("FEMALE/GIRLS")
                    .setType(ItemTypeEnum.CLOTH)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType3 = new ClothTypeEntity();
            clothType3
                    .setName("T-SHORTS")
                    .setGender("MALE/FEMALE/GIRLS/BOYS")
                    .setType(ItemTypeEnum.CLOTH)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType4 = new ClothTypeEntity();
            clothType4
                    .setName("JEANS")
                    .setGender("MALE/FEMALE/GIRLS/BOYS")
                    .setType(ItemTypeEnum.CLOTH)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType5 = new ClothTypeEntity();
            clothType5
                    .setName("TRACKSUITS")
                    .setGender("MALE/FEMALE")
                    .setType(ItemTypeEnum.CLOTH)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType6 = new ClothTypeEntity();
            clothType6
                    .setName("SWIMWEAR")
                    .setGender("MALE/FEMALE/GIRLS/BOYS")
                    .setType(ItemTypeEnum.CLOTH)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType7 = new ClothTypeEntity();
            clothType7
                    .setName("UNDERWEAR")
                    .setGender("MALE/FEMALE/GIRLS/BOYS")
                    .setType(ItemTypeEnum.CLOTH)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType8 = new ClothTypeEntity();
            clothType8
                    .setName("WINTER EQUIPMENTS")
                    .setGender("MALE/FEMALE/GIRLS/BOYS")
                    .setType(ItemTypeEnum.CLOTH)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType9 = new ClothTypeEntity();
            clothType9
                    .setName("BLOUSES")
                    .setGender("MALE/FEMALE/GIRLS/BOYS")
                    .setType(ItemTypeEnum.CLOTH)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType10 = new ClothTypeEntity();
            clothType10
                    .setName("ELETS")
                    .setGender("MALE/FEMALE/GIRLS/BOYS")
                    .setType(ItemTypeEnum.CLOTH)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());


            ClothTypeEntity clothType11 = new ClothTypeEntity();
            clothType11
                    .setName("VESTS")
                    .setGender("MALE/FEMALE/GIRLS/BOYS")
                    .setType(ItemTypeEnum.CLOTH)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType12 = new ClothTypeEntity();
            clothType12
                    .setName("WEDGES")
                    .setGender("MALE/FEMALE/GIRLS/BOYS")
                    .setType(ItemTypeEnum.CLOTH)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType13 = new ClothTypeEntity();
            clothType13
                    .setName("COSTUMES")
                    .setGender("MALE/FEMALE/GIRLS/BOYS")
                    .setType(ItemTypeEnum.CLOTH)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType14 = new ClothTypeEntity();
            clothType14
                    .setName("SHORTS")
                    .setGender("MALE/FEMALE/GIRLS/BOYS")
                    .setType(ItemTypeEnum.CLOTH)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType15 = new ClothTypeEntity();
            clothType15
                    .setName("COATS")
                    .setGender("MALE/FEMALE")
                    .setType(ItemTypeEnum.CLOTH)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType16 = new ClothTypeEntity();
            clothType16
                    .setName("PANTS")
                    .setGender("MALE/FEMALE/GIRLS/BOYS")
                    .setType(ItemTypeEnum.CLOTH)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType17 = new ClothTypeEntity();
            clothType17
                    .setName("MOUNTAIN EQUIPMENTS")
                    .setGender("MALE/FEMALE")
                    .setType(ItemTypeEnum.CLOTH)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType18 = new ClothTypeEntity();
            clothType18
                    .setName("POLY")
                    .setGender("MALE/FEMALE/GIRLS/BOYS")
                    .setType(ItemTypeEnum.CLOTH)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType19 = new ClothTypeEntity();
            clothType19
                    .setName("T-SHIRTS")
                    .setGender("MALE/FEMALE/GIRLS/BOYS")
                    .setType(ItemTypeEnum.CLOTH)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType20 = new ClothTypeEntity();
            clothType20
                    .setName("SWEATERS")
                    .setGender("MALE/FEMALE/GIRLS/BOYS")
                    .setType(ItemTypeEnum.CLOTH)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType21 = new ClothTypeEntity();
            clothType21
                    .setName("SHIRTS")
                    .setGender("MALE/FEMALE/GIRLS/BOYS")
                    .setType(ItemTypeEnum.CLOTH)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType22 = new ClothTypeEntity();
            clothType22
                    .setName("SPORTSWEAR")
                    .setGender("MALE/FEMALE/GIRLS/BOYS")
                    .setType(ItemTypeEnum.CLOTH)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType23 = new ClothTypeEntity();
            clothType23
                    .setName("TUNICS")
                    .setGender("MALE/FEMALE/GIRLS/BOYS")
                    .setType(ItemTypeEnum.CLOTH)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType24 = new ClothTypeEntity();
            clothType24
                    .setName("PANTS")
                    .setGender("MALE/FEMALE/GIRLS/BOYS")
                    .setType(ItemTypeEnum.CLOTH)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType25 = new ClothTypeEntity();
            clothType25
                    .setName("BICYCLE EQUIPMENTS")
                    .setGender("MALE/FEMALE/GIRLS/BOYS")
                    .setType(ItemTypeEnum.CLOTH)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity clothType26 = new ClothTypeEntity();
            clothType26
                    .setName("FOOTBALL EQUIPMENTS")
                    .setGender("MALE/FEMALE/BOYS")
                    .setType(ItemTypeEnum.CLOTH)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity accessories = new ClothTypeEntity();
            accessories
                    .setName("Belts")
                    .setGender("MALE/FEMALE/BOYS/GIRLS")
                    .setType(ItemTypeEnum.ACCESSORIES)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity accessories1 = new ClothTypeEntity();
            accessories1
                    .setName("Sets")
                    .setGender("MALE/FEMALE/BOYS/GIRLS")
                    .setType(ItemTypeEnum.ACCESSORIES)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity accessories2 = new ClothTypeEntity();
            accessories2
                    .setName("Cuffs")
                    .setGender("MALE/FEMALE/BOYS/GIRLS")
                    .setType(ItemTypeEnum.ACCESSORIES)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity accessories3 = new ClothTypeEntity();
            accessories3
                    .setName("Travel bags")
                    .setGender("MALE/FEMALE/BOYS/GIRLS")
                    .setType(ItemTypeEnum.ACCESSORIES)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity accessories4 = new ClothTypeEntity();
            accessories4
                    .setName("Purses")
                    .setGender("MALE/FEMALE/BOYS/GIRLS")
                    .setType(ItemTypeEnum.ACCESSORIES)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity accessories5 = new ClothTypeEntity();
            accessories5
                    .setName("Backpacks")
                    .setGender("MALE/FEMALE/BOYS/GIRLS")
                    .setType(ItemTypeEnum.ACCESSORIES)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity accessories6 = new ClothTypeEntity();
            accessories6
                    .setName("Gloves")
                    .setGender("MALE/FEMALE/BOYS/GIRLS")
                    .setType(ItemTypeEnum.ACCESSORIES)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity accessories7 = new ClothTypeEntity();
            accessories7
                    .setName("Bags")
                    .setGender("MALE/FEMALE/BOYS/GIRLS")
                    .setType(ItemTypeEnum.ACCESSORIES)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity accessories8 = new ClothTypeEntity();
            accessories8
                    .setName("Sunglasses")
                    .setGender("MALE/FEMALE/BOYS/GIRLS")
                    .setType(ItemTypeEnum.ACCESSORIES)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity accessories9 = new ClothTypeEntity();
            accessories9
                    .setName("Watches")
                    .setGender("MALE/FEMALE/BOYS/GIRLS")
                    .setType(ItemTypeEnum.ACCESSORIES)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity accessories10 = new ClothTypeEntity();
            accessories10
                    .setName("Scarves")
                    .setGender("MALE/FEMALE/BOYS/GIRLS")
                    .setType(ItemTypeEnum.ACCESSORIES)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity accessories11 = new ClothTypeEntity();
            accessories11
                    .setName("Hats")
                    .setGender("MALE/FEMALE/BOYS/GIRLS")
                    .setType(ItemTypeEnum.ACCESSORIES)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity accessories12 = new ClothTypeEntity();
            accessories12
                    .setName("Tie")
                    .setGender("MALE/BOYS")
                    .setType(ItemTypeEnum.ACCESSORIES)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity shoes = new ClothTypeEntity();
            shoes
                    .setName("Boots")
                    .setGender("MALE/FEMALE/BOYS/GIRLS")
                    .setType(ItemTypeEnum.SHOES)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity shoes1 = new ClothTypeEntity();
            shoes1
                    .setName("Low shoes")
                    .setGender("MALE/FEMALE/BOYS/GIRLS")
                    .setType(ItemTypeEnum.SHOES)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity shoes2 = new ClothTypeEntity();
            shoes2
                    .setName("Sneakers")
                    .setGender("MALE/FEMALE/BOYS/GIRLS")
                    .setType(ItemTypeEnum.SHOES)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity shoes3 = new ClothTypeEntity();
            shoes3
                    .setName("Low sandals")
                    .setGender("MALE/FEMALE/BOYS/GIRLS")
                    .setType(ItemTypeEnum.SHOES)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity shoes4 = new ClothTypeEntity();
            shoes4
                    .setName("High heels shoes")
                    .setGender("MALE/FEMALE/BOYS/GIRLS")
                    .setType(ItemTypeEnum.SHOES)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity shoes5 = new ClothTypeEntity();
            shoes5
                    .setName("Sandals")
                    .setGender("MALE/FEMALE/BOYS/GIRLS")
                    .setType(ItemTypeEnum.SHOES)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity shoes6 = new ClothTypeEntity();
            shoes6
                    .setName("Heeled sandals")
                    .setGender("MALE/FEMALE/BOYS/GIRLS")
                    .setType(ItemTypeEnum.SHOES)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity shoes7 = new ClothTypeEntity();
            shoes7
                    .setName("Sports / Casual shoes")
                    .setGender("MALE/FEMALE/BOYS/GIRLS")
                    .setType(ItemTypeEnum.SHOES)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity shoes8 = new ClothTypeEntity();
            shoes8
                    .setName("Hiking Shoes")
                    .setGender("MALE/FEMALE/BOYS/GIRLS")
                    .setType(ItemTypeEnum.SHOES)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            ClothTypeEntity shoes9 = new ClothTypeEntity();
            shoes9
                    .setName("Slippers")
                    .setGender("MALE/FEMALE/BOYS/GIRLS")
                    .setType(ItemTypeEnum.SHOES)
                    .setCreated(Instant.now())
                    .setModified(Instant.now());


            this.clothTypeRepository.saveAll(
                    List.of(clothType, clothType2, clothType3, clothType4, clothType5, clothType6, clothType7,
                            clothType8, clothType9, clothType10, clothType11, clothType12, clothType13, clothType14, clothType15, clothType16,
                            clothType17, clothType18, clothType19, clothType20, clothType21, clothType22, clothType23, clothType24, clothType25,
                            clothType26,accessories,accessories1,accessories2,accessories3,accessories4,accessories5,accessories6,accessories7,accessories8,
                            accessories9,accessories10,accessories11,accessories12,shoes,shoes1,shoes2,shoes3,shoes4,shoes5,shoes6,shoes7,shoes8,shoes9)
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
