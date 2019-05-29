package jdurao.kschool.entities;

import jdurao.kschool.pojo.ArtistJson;
import jdurao.kschool.pojo.types.AreaJsonType;
import jdurao.kschool.pojo.types.ArtistJsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity(name = "Artists")
@TypeDef(name = "ArtistJsonType", typeClass = ArtistJsonType.class)
@Table(name = "artists")
public class Artists {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    @Type(type = "ArtistJsonType")
    private ArtistJson artist;

    public Artists() {
    }

    public Artists(ArtistJson artist) {
        this.artist = artist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArtistJson getArtist() {
        return artist;
    }

    public void setArtist(ArtistJson artist) {
        this.artist = artist;
    }
}