package jdurao.kschool.entities;

import jdurao.kschool.pojo.PlaceJson;
import jdurao.kschool.pojo.types.MediumJsonType;
import jdurao.kschool.pojo.types.PlaceJsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity(name = "Places")
@TypeDef(name = "PlaceJsonType", typeClass = PlaceJsonType.class)
@Table(name = "places")
public class Places {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    @Type(type = "PlaceJsonType")
    private PlaceJson place;

    public Places() {
    }

    public Places(PlaceJson place) {
        this.place = place;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlaceJson getPlace() {
        return place;
    }

    public void setPlace(PlaceJson place) {
        this.place = place;
    }
}