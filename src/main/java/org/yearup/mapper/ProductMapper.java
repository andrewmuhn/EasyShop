package org.yearup.mapper;
import org.mapstruct.Mapper;
import org.yearup.models.Product;
import org.yearup.models.dto.ProductDTO;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toProductDTO(Product product);
    Product toProduct(ProductDTO productDTO);
}
