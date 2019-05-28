package jdurao.kschool.pojo;

import java.io.Serializable;

public class MediumJson implements Serializable {
    private Long id;
    private Long formatId;
    private String name;
    private String updatedDate;

    public MediumJson() {
    }

    public MediumJson(Long id, Long formatId, String name, String updatedDate) {
        this.id = id;
        this.formatId = formatId;
        this.name = name;
        this.updatedDate = updatedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFormatId() {
        return formatId;
    }

    public void setFormatId(Long formatId) {
        this.formatId = formatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }
}
