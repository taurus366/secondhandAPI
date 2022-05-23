package com.secondhand.secondhand.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedEntityGraph(
        name="country",
        attributeNodes = {
                @NamedAttributeNode("countryEntity"),
                @NamedAttributeNode("region")
        }
)
@NamedEntityGraph(
        name = "addresses",
        attributeNodes = {
                @NamedAttributeNode("speedyAddresses")
        }
)
//@NamedEntityGraph(
//        name = "user-likes",
//        attributeNodes = {
//                @NamedAttributeNode("likesList"),
//                @NamedAttributeNode(value = "likesList", subgraph = "likes"),
//        },
//        subgraphs = {
//                @NamedSubgraph(
//                        name = "likes",
//                        attributeNodes = {
//                                @NamedAttributeNode("coverPicture"),
//                                @NamedAttributeNode("clothComposition"),
//                                @NamedAttributeNode("frontPicture"),
//                                @NamedAttributeNode("clothType"),
//                                @NamedAttributeNode("clothBrandEntity")
//                        }
//                )
//        }
//)

@Entity
@Table(name = "speedy_cities")
public class SpeedyCityEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int postCode;

    @OneToOne
    private RegionEntity region;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<SpeedyAddressEntity> speedyAddresses = new ArrayList<>();

    @ManyToOne()
    private CountryEntity countryEntity;

    public SpeedyCityEntity() {
    }

    public String getName() {
        return name;
    }

    public SpeedyCityEntity setName(String name) {
        this.name = name;
        return this;
    }

    public int getPostCode() {
        return postCode;
    }

    public SpeedyCityEntity setPostCode(int postCode) {
        this.postCode = postCode;
        return this;
    }

    public RegionEntity getRegion() {
        return region;
    }

    public SpeedyCityEntity setRegion(RegionEntity region) {
        this.region = region;
        return this;
    }

    public List<SpeedyAddressEntity> getSpeedyAddresses() {
        return speedyAddresses;
    }

    public SpeedyCityEntity setSpeedyAddresses(List<SpeedyAddressEntity> speedyAddresses) {
        this.speedyAddresses = speedyAddresses;
        return this;
    }

    public CountryEntity getCountryEntity() {
        return countryEntity;
    }

    public SpeedyCityEntity setCountryEntity(CountryEntity countryEntity) {
        this.countryEntity = countryEntity;
        return this;
    }
}
