package jdurao.kschool.pojo;

import java.io.Serializable;

public class ArtistJson implements Serializable {
    private Long id;
    private Long areaId;
    private String name;
    private String sortName;
    private String updatedDate;

    public ArtistJson() {
    }

    public ArtistJson(Long id, Long areaId, String name, String sortName, String updatedDate) {
        this.id = id;
        this.areaId = areaId;
        this.name = name;
        this.sortName = sortName;
        this.updatedDate = updatedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
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

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }
}
