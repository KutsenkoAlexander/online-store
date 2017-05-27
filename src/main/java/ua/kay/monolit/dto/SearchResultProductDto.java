package ua.kay.monolit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchResultProductDto {
    @JsonProperty("idProduct")
    private long idProduct;

    @JsonProperty("title")
    private String title;

    public SearchResultProductDto() {
    }

    public SearchResultProductDto(long idProduct, String title) {
        this.idProduct = idProduct;
        this.title = title;
    }

    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
