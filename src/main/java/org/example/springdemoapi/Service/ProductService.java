package org.example.springdemoapi.Service;

import lombok.RequiredArgsConstructor;
import org.example.springdemoapi.Dto.ApiResponse;
import org.example.springdemoapi.Dto.Request.CreateProductRequest;
import org.example.springdemoapi.Dto.Request.ProductUpdateRequest;
import org.example.springdemoapi.Dto.Response.CreateProductResponse;
import org.example.springdemoapi.Dto.Response.ProductResponse;
import org.example.springdemoapi.Dto.Response.ProductUpdateResponse;
import org.example.springdemoapi.Entity.Product;
import org.example.springdemoapi.Mapper.ProductMapper;
import org.example.springdemoapi.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    //? Các Step thực hiện
    //? 1. Inject repository / mapper
    //? 2. Tạo method để handle Logic
            //? Xác định Kiểu Dữ liệu cần nhận và gửi
            //?

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper  productMapper;

    public List<ProductResponse> getAllProducts(){
        var products = productRepository.findAll();
        return products.stream().map(productMapper::toGetProductResponse).toList();
    }

    public ProductResponse getProductDetail(String productId){
        var products = productRepository.findById(productId);
        return products.map(productMapper::toGetProductResponse).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product createProduct(CreateProductRequest createProductRequest){

        Product product = productMapper.toCreateProduct(createProductRequest);

        return productRepository.save(product);
    }

    public ProductUpdateResponse updateProduct(String productId, ProductUpdateRequest updateProductRequest){
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        productMapper.updateProduct(product, updateProductRequest);

        return productMapper.toUpdateProductResponse(product);
    }

    public void deleteProduct(String productId){
        productRepository.deleteById(productId);
    }

}
