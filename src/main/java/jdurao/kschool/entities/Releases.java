package jdurao.kschool.entities;

import jdurao.kschool.pojo.ReleaseJson;
import jdurao.kschool.pojo.types.PlaceJsonType;
import jdurao.kschool.pojo.types.ReleaseJsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity(name = "Releases")
@TypeDef(name = "ReleaseJsonType", typeClass = ReleaseJsonType.class)
@Table(name = "releases")
public class Releases {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    @Type(type = "ReleaseJsonType")
    private ReleaseJson release;

    public Releases() {
    }

    public Releases(ReleaseJson release) {
        this.release = release;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReleaseJson getRelease() {
        return release;
    }

    public void setRelease(ReleaseJson release) {
        this.release = release;
    }
}