package org.example.comic_manga_polica.dto;

import jakarta.validation.constraints.*;

public class ReviewRequest {
    @NotNull(message = "rating required")
    @Min(value= 1, message = "minimum rating is 1")
    @Max(value= 5, message = "maximum rating is 5")
    private Integer zvjezdice;

    @Size(max= 500, message = "comment too long")
    private String komentar;

    @NotNull(message = "bookshelf required")
    private Long shelfId;

    public Integer getZvjezdice() {
        return zvjezdice;
    }

    public void setZvjezdice(Integer zvjezdice) {
        this.zvjezdice = zvjezdice;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }
}
