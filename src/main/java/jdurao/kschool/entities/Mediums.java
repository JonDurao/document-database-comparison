package jdurao.kschool.entities;

import jdurao.kschool.pojo.MediumJson;
import jdurao.kschool.pojo.types.LanguageJsonType;
import jdurao.kschool.pojo.types.MediumJsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity(name = "Mediums")
@TypeDef(name = "MediumJsonType", typeClass = MediumJsonType.class)
@Table(name = "mediums")
public class Mediums {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    @Type(type = "MediumJsonType")
    private MediumJson medium;

    public Mediums() {
    }

    public Mediums(MediumJson medium) {
        this.medium = medium;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MediumJson getMedium() {
        return medium;
    }

    public void setMedium(MediumJson medium) {
        this.medium = medium;
    }
}