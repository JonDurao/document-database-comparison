package jdurao.kschool.entities;

import jdurao.kschool.pojo.LanguageJson;
import jdurao.kschool.pojo.types.LabelJsonType;
import jdurao.kschool.pojo.types.LanguageJsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity(name = "Languages")
@TypeDef(name = "LanguageJsonType", typeClass = LanguageJsonType.class)
@Table(name = "languages")
public class Languages {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    @Type(type = "LanguageJsonType")
    private LanguageJson language;

    public Languages() {
    }

    public Languages(LanguageJson language) {
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LanguageJson getLanguage() {
        return language;
    }

    public void setLanguage(LanguageJson language) {
        this.language = language;
    }
}