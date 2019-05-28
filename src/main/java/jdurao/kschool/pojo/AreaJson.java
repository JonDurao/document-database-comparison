package jdurao.kschool.pojo;

import java.io.Serializable;

public class AreaJson implements Serializable {
    private Long id;
    private Long placeId;
    private String name;
    private String sortName;
    private String comment;
    private String updatedDate;

    public AreaJson() {
    }

    public AreaJson(Long id, Long placeId, String name, String sortName, String comment, String updatedDate) {
        this.id = id;
        this.placeId = placeId;
        this.name = name;
        this.sortName = sortName;
        this.comment = comment;
        this.updatedDate = updatedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
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
