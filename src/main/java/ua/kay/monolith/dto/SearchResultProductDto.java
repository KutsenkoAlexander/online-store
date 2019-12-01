package ua.kay.monolith.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchResultProductDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    public SearchResultProductDto() {
    }

    public SearchResultProductDto(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
