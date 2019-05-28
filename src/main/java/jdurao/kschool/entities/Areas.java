package jdurao.kschool.entities;

import jdurao.kschool.pojo.AreaJson;

import javax.persistence.*;

@Entity(name = "areas")
public class Areas {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column
    private AreaJson area;

    public Areas() {
    }

    public Areas(AreaJson area) {
        this.area = area;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AreaJson getArea() {
        return area;
    }

    public void setArea(AreaJson area) {
        this.area = area;
    }
}