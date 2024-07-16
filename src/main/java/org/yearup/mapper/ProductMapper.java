package org.yearup.mapper;
import org.mapstruct.Mapper;
import org.yearup.models.Product;
import org.yearup.models.dto.CreateProductDTO;
import org.yearup.models.dto.ProductDTO;
import org.yearup.models.dto.UpdateProductDTO;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toProductDTO(Product product);
//    Product toProduct(ProductDTO productDTO);

    Product fromCreateProductDTO(CreateProductDTO dto);
    Product fromUpdateProductDTO(UpdateProductDTO dto);
}
