package org.yearup.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UpdateCategoryDTO {
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name can be at most 100 characters")
    private String name;
    @NotBlank (message = "Description is required")
    private String description;

    public UpdateCategoryDTO() {
    }

    public UpdateCategoryDTO(String name, String description) {
        this.name = name;
        this.description = description;
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
