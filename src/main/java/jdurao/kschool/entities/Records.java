package jdurao.kschool.entities;

import jdurao.kschool.pojo.RecordJson;
import jdurao.kschool.pojo.types.RecordJsonType;
import jdurao.kschool.pojo.types.TrackJsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity(name = "Records")
@TypeDef(name = "RecordJsonType", typeClass = RecordJsonType.class)
@Table(name = "records")
public class Records {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    @Type(type = "RecordJsonType")
    private RecordJson record;

    public Records() {
    }

    public Records(RecordJson record) {
        this.record = record;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RecordJson getRecord() {
        return record;
    }

    public void setRecord(RecordJson record) {
        this.record = record;
    }
}
