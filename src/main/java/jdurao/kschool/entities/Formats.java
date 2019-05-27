package jdurao.kschool.entities;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity(name = "Formats")
@Table(name = "formats")
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class Formats {
    @Id
    @GeneratedValue
    private Long id;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private String format;

    public Formats() {}

    public Formats(String format) {
        this.format = format;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}