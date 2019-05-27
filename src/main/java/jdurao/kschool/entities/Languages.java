package jdurao.kschool.entities;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity(name = "Languages")
@Table(name = "languages")
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class Languages {
    @Id
    @GeneratedValue
    private Long id;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private String language;

    public Languages() {}

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