package jdurao.kschool.pojo;

import java.io.Serializable;

public class LabelJson implements Serializable {
    private Long id;
    private Long areaId;
    private String name;
    private Long code;
    private String sortName;
    private String comment;
    private String updatedDate;

    public LabelJson() {
    }

    public LabelJson(Long id, Long areaId, String name, Long code, String sortName, String comment, String updatedDate) {
        this.id = id;
        this.areaId = areaId;
        this.name = name;
        this.code = code;
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

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
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
