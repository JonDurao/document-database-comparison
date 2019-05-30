package jdurao.kschool.entities;

import jdurao.kschool.pojo.LabelJson;
import jdurao.kschool.pojo.types.FormatJsonType;
import jdurao.kschool.pojo.types.LabelJsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity(name = "Labels")
@TypeDef(name = "LabelJsonType", typeClass = LabelJsonType.class)
@Table(name = "labels")
public class Labels {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    @Type(type = "LabelJsonType")
    private LabelJson label;

    public Labels() {
    }

    public Labels(LabelJson label) {
        this.label = label;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LabelJson getLabel() {
        return label;
    }

    public void setLabel(LabelJson label) {
        this.label = label;
    }
}