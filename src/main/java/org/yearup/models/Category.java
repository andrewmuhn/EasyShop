package org.yearup.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@EntityListeners(AuditingEntityListener.class)
@Entity
public class Category
{
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long categoryId;
    @NotBlank (message = "Name is required")
    private String name;
    @NotBlank (message = "Description is required")
    private String description;

    public Category()
    {
    }

    public Category(Long categoryId, String name, String description)
    {
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

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
