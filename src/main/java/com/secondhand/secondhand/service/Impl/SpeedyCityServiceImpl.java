package com.secondhand.secondhand.service.Impl;

import com.secondhand.secondhand.model.dto.CityDTO;
import com.secondhand.secondhand.model.dto.SpeedyAddressFormDTO;
import com.secondhand.secondhand.model.entity.CountryEntity;
import com.secondhand.secondhand.model.entity.RegionEntity;
import com.secondhand.secondhand.model.entity.SpeedyAddressEntity;
import com.secondhand.secondhand.model.entity.SpeedyCityEntity;
import com.secondhand.secondhand.model.service.JsonSpeedyCityServiceModel;
import com.secondhand.secondhand.repository.CountryRepository;
import com.secondhand.secondhand.repository.RegionRepository;
import com.secondhand.secondhand.repository.SpeedyCityRepository;
import com.secondhand.secondhand.service.SpeedyCityService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SpeedyCityServiceImpl implements SpeedyCityService {

    private final SpeedyCityRepository speedyCityRepository;
    private final RegionRepository regionRepository;
    private final CountryRepository countryRepository;

    public SpeedyCityServiceImpl(SpeedyCityRepository speedyCityRepository, RegionRepository regionRepository, CountryRepository countryRepository) {
        this.speedyCityRepository = speedyCityRepository;
        this.regionRepository = regionRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<SpeedyCityEntity> getAllCities() {
        return this.speedyCityRepository
                .findAll();
    }

    @Override
    public void postAllCities(List<JsonSpeedyCityServiceModel> jsonSpeedyCityServiceModels) {

//    List<RegionEntity> usedRegions = new ArrayList<>();
    Map<String,RegionEntity> usedRegions = new HashMap<>();
    Map<String, CountryEntity> usedCountries = new HashMap<>();

    jsonSpeedyCityServiceModels
            .forEach(jsonSpeedyCityServiceModel -> {

                SpeedyCityEntity speedyCity = asSpeedyCityEntity(jsonSpeedyCityServiceModel);


//                SET ADDRESSES !
                jsonSpeedyCityServiceModel
                        .getAddress()
                        .forEach(jsonSpeedyAddressListServiceModel -> {
                            SpeedyAddressEntity speedyAddressEntity = new SpeedyAddressEntity();
                            speedyAddressEntity
                                    .setName(jsonSpeedyAddressListServiceModel.getName())
                                    .setCreated(Instant.now())
                                    .setModified(Instant.now());
                            speedyCity
                                    .getSpeedyAddresses().add(speedyAddressEntity);
                        });


//                SET REGION !
                String region = jsonSpeedyCityServiceModel.getRegion();
                if (!usedRegions.containsKey(region)) {
                    RegionEntity byName = this.regionRepository
                            .findByName(region);
                    usedRegions
                            .put(region, byName);
                } else {

                    RegionEntity region1 = usedRegions.get(region);

                    speedyCity.setRegion(region1);
                }

//                SET COUNTRY !
                String country = jsonSpeedyCityServiceModel.getCountry();
                if (!usedCountries.containsKey(country)) {
//                    TODO -> HERE CAN THROW SOME ERROR! IF can't find the country in DB
                    CountryEntity byName = this.countryRepository
                            .getCountryGraphSpeedy(country);
                    usedCountries.put(country,byName);
                } else {
                    CountryEntity countryEntity = usedCountries.get(country);
                    countryEntity
                            .getSpeedyCityEntities()
                            .add(speedyCity);
                    speedyCity
                            .setCountryEntity(countryEntity);
                }


                this.speedyCityRepository
                        .save(speedyCity);

            });

        usedCountries
                .forEach((s, countryEntity) -> {
                    this.countryRepository
                            .save(countryEntity);
                });

    }

    @Override
    public List<CityDTO> getCityByStringLike(String city) {

        return this.speedyCityRepository
                .findSpeedyCityByStringGraphCountry(city)
                .stream()
                .map(this::asSpeedyCityDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SpeedyAddressFormDTO> getSpeedyAddressesByCityId(Long cityId) {

        List<SpeedyAddressFormDTO> speedyAddressByCityIdGraphAddress = this.speedyCityRepository
                .findSpeedyAddressByCityIdGraphAddress(cityId)
                .orElseThrow( () -> new NullPointerException("We couldn't find the city"))
                .getSpeedyAddresses()
                .stream()
                .map(this::asSpeedyAddressDTO)
                .collect(Collectors.toList());

        return speedyAddressByCityIdGraphAddress;
    }

    @Override
    public SpeedyCityEntity getCityByIdTogetherAddresses(Long id) {
        return this.speedyCityRepository
                .findSpeedyAddressByCityIdGraphAddress(id).orElseThrow( () -> new UsernameNotFoundException("speedyCityServiceImpl line 140"));
    }

    private SpeedyAddressFormDTO asSpeedyAddressDTO(SpeedyAddressEntity speedyAddressEntity) {
        SpeedyAddressFormDTO speedyAddressFormDTO = new SpeedyAddressFormDTO();
        speedyAddressFormDTO
                .setId(speedyAddressEntity.getId())
                .setName(speedyAddressEntity.getName());

        return speedyAddressFormDTO;
    }


    private CityDTO asSpeedyCityDTO(SpeedyCityEntity speedyCityEntity){

        CityDTO cityDTO = new CityDTO();
        cityDTO
                .setCity(speedyCityEntity.getName())
                .setCountry(speedyCityEntity.getCountryEntity().getName())
                .setCountryCode(speedyCityEntity.getCountryEntity().getCode())
                .setId(speedyCityEntity.getId())
                .setRegion(speedyCityEntity.getRegion().getName())
                .setPostCode(String.valueOf(speedyCityEntity.getPostCode()));

        return cityDTO;
    }


//    USED BY POSTALLCITIES FROM INIT DATABASE
    private SpeedyCityEntity asSpeedyCityEntity(JsonSpeedyCityServiceModel jsonSpeedyCityServiceModel){

        SpeedyCityEntity speedyCityEntity = new SpeedyCityEntity();
        speedyCityEntity
                .setName(jsonSpeedyCityServiceModel.getCity())
                .setPostCode(jsonSpeedyCityServiceModel.getPostCode())
                .setCreated(Instant.now())
                .setModified(Instant.now());

        return speedyCityEntity;
    }
}
