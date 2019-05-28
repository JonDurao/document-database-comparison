package jdurao.kschool.entities;

import jdurao.kschool.pojo.AreaJson;
import jdurao.kschool.pojo.types.AreaJsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity(name = "areas")
@TypeDef(name = "AreaJsonType", typeClass = AreaJsonType.class)
public class Areas {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column
    @Type(type = "AreaJsonType")
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Areas)) {
            return false;
        }
        Areas other = (Areas) obj;
        if (id != null) {
            if (!id.equals(other.id)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " ";
        if (area != null)
            result += "area: " + area.toString();
        return result;
    }
}