package jdurao.kschool.entities;

import jdurao.kschool.pojo.TrackJson;
import jdurao.kschool.pojo.types.ArtistJsonType;
import jdurao.kschool.pojo.types.TrackJsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity(name = "Tracks")
@TypeDef(name = "TrackJsonType", typeClass = TrackJsonType.class)
@Table(name = "tracks")
public class Tracks {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    @Type(type = "TrackJsonType")
    private TrackJson track;

    public Tracks() {
    }

    public Tracks(TrackJson track) {
        this.track = track;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrackJson getTrack() {
        return track;
    }

    public void setTrack(TrackJson track) {
        this.track = track;
    }
}
