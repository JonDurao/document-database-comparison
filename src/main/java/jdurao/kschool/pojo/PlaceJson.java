package jdurao.kschool.pojo;

import java.io.Serializable;

public class PlaceJson implements Serializable {
    private Long id;
    private String name;
    private String address;
    private String coordinates;
    private String updatedDate;

    public PlaceJson() {
    }

    public PlaceJson(Long id, String name, String address, String coordinates, String updatedDate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.coordinates = coordinates;
        this.updatedDate = updatedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }
}
