package jdurao.kschool.pojo;

import java.io.Serializable;

public class ReleaseJson implements Serializable {
    private Long id;
    private Long artistId;
    private Long recordId;
    private Long languageId;
    private Long labelId;
    private Long mediumId;
    private Long barcode;
    private String status;
    private Long quality;
    private String comment;
    private String updatedDate;

    public ReleaseJson() {
    }

    public ReleaseJson(Long id, Long artistId, Long recordId, Long languageId, Long labelId, Long mediumId, Long barcode, String status, Long quality, String comment, String updatedDate) {
        this.id = id;
        this.artistId = artistId;
        this.recordId = recordId;
        this.languageId = languageId;
        this.labelId = labelId;
        this.mediumId = mediumId;
        this.barcode = barcode;
        this.status = status;
        this.quality = quality;
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

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public Long getMediumId() {
        return mediumId;
    }

    public void setMediumId(Long mediumId) {
        this.mediumId = mediumId;
    }

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getQuality() {
        return quality;
    }

    public void setQuality(Long quality) {
        this.quality = quality;
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
