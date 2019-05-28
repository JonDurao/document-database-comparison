package jdurao.kschool.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Mediums")
@Table(name = "mediums")
public class Mediums {
    @Id
    @GeneratedValue
    private Long id;

    private String medium;

    public Mediums() {
    }

    public Mediums(String medium) {
        this.medium = medium;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}