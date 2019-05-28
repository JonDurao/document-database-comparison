package jdurao.kschool.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Languages")
@Table(name = "languages")
public class Languages {
    @Id
    @GeneratedValue
    private Long id;

    private String language;

    public Languages() {
    }

    public Languages(String language) {
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}