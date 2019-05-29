package jdurao.kschool.pojo;

import java.io.Serializable;

public class AreaJson implements Serializable {
    private Integer id;
    private Integer placeId;
    private String name;
    private String sortName;
    private String comment;
    private String updatedDate;

    public AreaJson() {
    }

    public AreaJson(Integer id, Integer placeId, String name, String sortName, String comment, String updatedDate) {
        this.id = id;
        this.placeId = placeId;
        this.name = name;
        this.sortName = sortName;
        this.comment = comment;
        this.updatedDate = updatedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
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
