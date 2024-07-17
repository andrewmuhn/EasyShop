package org.yearup.models;

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

    public Category()
    {
    }

    public Category(Long categoryId, String name, String description, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
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

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    protected void onCreate() { createdDate = LocalDateTime.now(ZoneOffset.UTC); }
    protected void onUpdate() { lastModifiedDate = LocalDateTime.now(ZoneOffset.UTC); }
}
