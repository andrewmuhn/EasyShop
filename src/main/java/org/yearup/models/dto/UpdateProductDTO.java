package org.yearup.models.dto;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class UpdateProductDTO {
    @NotBlank(message = "product must have a name")
    private String name;

    @NotBlank(message = "product must have a price")
    private BigDecimal price;
    @NotBlank(message = "product must be categorized")
    private int categoryId;
    private String description;
    private String color;
    @NotBlank(message = "must add stock")
    private int stock;
    private boolean isFeatured;
    private String imageUrl;
    private Timestamp lastModifiedDate;

    public UpdateProductDTO()
    {
    }

    public UpdateProductDTO(String name, BigDecimal price, int categoryId, String description,
            String color,
            int stock, boolean isFeatured, String imageUrl, Timestamp lastModifiedDate)
    {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.description = description;
        this.color = color;
        this.stock = stock;
        this.isFeatured = isFeatured;
        this.imageUrl = imageUrl;
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public int getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(int categoryId)
    {
        this.categoryId = categoryId;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public int getStock()
    {
        return stock;
    }

    public void setStock(int stock)
    {
        this.stock = stock;
    }

    public boolean isFeatured()
    {
        return isFeatured;
    }

    public void setFeatured(boolean featured)
    {
        isFeatured = featured;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
