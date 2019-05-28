package jdurao.kschool.pojo;

import java.io.Serializable;

public class LanguageJson implements Serializable {
    private Long languageId;
    private String name;

    public LanguageJson() {
    }

    public LanguageJson(Long languageId, String name) {
        this.languageId = languageId;
        this.name = name;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
