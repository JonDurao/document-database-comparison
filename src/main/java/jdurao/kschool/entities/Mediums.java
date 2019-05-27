package jdurao.kschool.entities;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity(name = "Mediums")
@Table(name = "mediums")
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class Mediums {
    @Id
    @GeneratedValue
    private Long id;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private String medium;

    public Mediums() {}

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