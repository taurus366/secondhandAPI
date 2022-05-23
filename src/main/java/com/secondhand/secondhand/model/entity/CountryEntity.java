package com.secondhand.secondhand.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NamedEntityGraph(
        name = "speedy",
        attributeNodes = {
                @NamedAttributeNode("speedyCityEntities")
        }
)

@Entity()
@Table(name = "countries")
public class CountryEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<SpeedyCityEntity> speedyCityEntities = new ArrayList<>();

    public CountryEntity() {
    }

    public String getName() {
        return name;
    }

    public CountryEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public CountryEntity setCode(String code) {
        this.code = code;
        return this;
    }

    public List<SpeedyCityEntity> getSpeedyCityEntities() {
        return speedyCityEntities;
    }

    public CountryEntity setSpeedyCityEntities(List<SpeedyCityEntity> speedyCityEntities) {
        this.speedyCityEntities = speedyCityEntities;
        return this;
    }
}
