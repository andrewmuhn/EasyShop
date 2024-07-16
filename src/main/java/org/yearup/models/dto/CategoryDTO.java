package org.yearup.models.dto;

import javax.validation.constraints.NotBlank;

public class CategoryDTO {
    private Long categoryId;
    @NotBlank  (message = "Name is required")
    private String name;
    @NotBlank (message = "Description is required")
    private String description;

    public CategoryDTO() {
    }

    public CategoryDTO(Long categoryId, String name, String description) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
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
}
