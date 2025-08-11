package org.example.springdemoapi.Mapper;

import org.example.springdemoapi.Dto.Request.CreateProductRequest;
import org.example.springdemoapi.Dto.Request.ProductUpdateRequest;
import org.example.springdemoapi.Dto.Response.ProductResponse;
import org.example.springdemoapi.Dto.Response.ProductUpdateResponse;
import org.example.springdemoapi.Entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toCreateProduct(CreateProductRequest request);

    ProductResponse toGetProductResponse(Product product);

    ProductUpdateResponse toUpdateProductResponse(Product product);

    void updateProduct(@MappingTarget Product product, ProductUpdateRequest request);

}
