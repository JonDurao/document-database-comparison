package jdurao.kschool.pojo;

import java.io.Serializable;

public class TrackJson implements Serializable {
    private Long id;
    private Long artistId;
    private Long recordId;
    private String name;
    private Long length;
    private Long number;
    private String comment;
    private String updatedDate;

    public TrackJson() {
    }

    public TrackJson(Long id, Long artistId, Long recordId, String name, Long length, Long number, String comment, String updatedDate) {
        this.id = id;
        this.artistId = artistId;
        this.recordId = recordId;
        this.name = name;
        this.length = length;
        this.number = number;
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

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
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

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
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
