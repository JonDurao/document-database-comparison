package jdurao.kschool.pojo;

import java.io.Serializable;

public class AreaJson implements Serializable {
    private Long areaId;
    private String name;
    private String sortName;
    private String comment;
    private String updatedTime;

    public AreaJson() {
    }

    public AreaJson(Long areaId, String name, String sortName, String comment, String updatedTime) {
        this.areaId = areaId;
        this.name = name;
        this.sortName = sortName;
        this.comment = comment;
        this.updatedTime = updatedTime;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }
}
