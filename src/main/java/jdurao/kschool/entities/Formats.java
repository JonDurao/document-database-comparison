package jdurao.kschool.entities;

import jdurao.kschool.pojo.FormatJson;
import jdurao.kschool.pojo.types.ArtistJsonType;
import jdurao.kschool.pojo.types.FormatJsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity(name = "Formats")
@TypeDef(name = "FormatJsonType", typeClass = FormatJsonType.class)
@Table(name = "formats")
public class Formats {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    @Type(type = "FormatJsonType")
    private FormatJson format;

    public Formats() {
    }

    public Formats(FormatJson format) {
        this.format = format;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FormatJson getFormat() {
        return format;
    }

    public void setFormat(FormatJson format) {
        this.format = format;
    }
}