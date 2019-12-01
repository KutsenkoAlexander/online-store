package ua.kay.monolith.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BreadcrumbsDto {
    @JsonProperty("idCategory")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("parentId")
    private Long parentId;

    public BreadcrumbsDto() {
    }

    public BreadcrumbsDto(Long id, String name, Long parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "BreadcrumbsDto{" +
                "idCategory=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
