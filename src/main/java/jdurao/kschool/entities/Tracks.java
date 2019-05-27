package jdurao.kschool.entities;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity(name = "Tracks")
@Table(name = "tracks")
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class Tracks {
    @Id
    @GeneratedValue
    private Long id;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private String track;

    public Tracks() {}

    public Tracks(String track) {
        this.track = track;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }
}
