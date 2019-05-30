package jdurao.kschool.enums;

public enum DataTablesEnum {
    AREAS("areas"),
    ARTISTS("artists"),
    FORMATS("formats"),
    LABELS("labels"),
    LANGUAGES("languages"),
    MEDIUMS("mediums"),
    PLACES("places"),
    RECORDS("records"),
    RELEASES("releases"),
    TRACKS("tracks");

    private String name;

    DataTablesEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
