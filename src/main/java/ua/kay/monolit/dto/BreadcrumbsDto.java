package ua.kay.monolit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BreadcrumbsDto {
    @JsonProperty("idCategory")
    private int idCategory;

    @JsonProperty("name")
    private String name;

    @JsonProperty("parentId")
    private int parentId;

    public BreadcrumbsDto() {
    }

    public BreadcrumbsDto(int idCategory, String name, int parentId) {
        this.idCategory = idCategory;
        this.name = name;
        this.parentId = parentId;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "BreadcrumbsDto{" +
                "idCategory=" + idCategory +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
