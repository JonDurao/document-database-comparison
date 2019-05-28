package jdurao.kschool.pojo;

import java.io.Serializable;

public class RecordJson implements Serializable {
    private Long id;
    private Long artistId;
    private String name;
    private Long length;
    private String comment;
    private String updatedDate;

    public RecordJson() {
    }

    public RecordJson(Long id, Long artistId, String name, Long length, String comment, String updatedDate) {
        this.id = id;
        this.artistId = artistId;
        this.name = name;
        this.length = length;
        this.comment = comment;
        this.updatedDate = updatedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }
}
