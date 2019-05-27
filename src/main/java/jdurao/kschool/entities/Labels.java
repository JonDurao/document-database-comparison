package jdurao.kschool.entities;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity(name = "Labels")
@Table(name = "labels")
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class Labels {
    @Id
    @GeneratedValue
    private Long id;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private String label;

    public Labels() {}

    public Labels(String label) {
        this.label = label;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}