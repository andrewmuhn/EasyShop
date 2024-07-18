package org.yearup.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.validation.constraints.NotBlank;



public class Category
{
    private Long categoryId;
    @NotBlank (message = "Name is required")
    private String name;
    @NotBlank (message = "Description is required")
    private String description;
    private Timestamp createdDate;
    private Timestamp lastModifiedDate;

    public Category()
    {
    }

    public Category(Long categoryId, String name, String description, Timestamp createdDate, Timestamp lastModifiedDate) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(Long categoryId)
    {
        this.categoryId = categoryId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
