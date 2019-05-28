package jdurao.kschool.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Labels")
@Table(name = "labels")
public class Labels {
    @Id
    @GeneratedValue
    private Long id;

    private String label;

    public Labels() {
    }

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