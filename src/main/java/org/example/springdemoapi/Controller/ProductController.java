package org.example.springdemoapi.Controller;


import jakarta.validation.Valid;
import org.example.springdemoapi.Dto.ApiResponse;
import org.example.springdemoapi.Dto.Request.CreateProductRequest;
import org.example.springdemoapi.Dto.Request.ProductUpdateRequest;
import org.example.springdemoapi.Dto.Response.CreateProductResponse;
import org.example.springdemoapi.Dto.Response.ProductResponse;
import org.example.springdemoapi.Dto.Response.ProductUpdateResponse;
import org.example.springdemoapi.Entity.Product;
import org.example.springdemoapi.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/get-products")
    ApiResponse<List<ProductResponse>> getProduct(){
//        ApiResponse<List<ProductResponse>> apiResponse = new ApiResponse<>();
//        apiResponse.setData(productService.getAllProducts());


        return ApiResponse.<List<ProductResponse>>builder()
                .data(productService.getAllProducts())
                .build();
    }

    @GetMapping("/product-detail/{productId}")
    ApiResponse<ProductResponse> getProductDetail(@PathVariable String productId){
        return ApiResponse.<ProductResponse>builder()
                .data(productService.getProductDetail(productId))
                .build();
    }


    @PostMapping("/create-product")
    ApiResponse<Product> createProduct(@RequestBody @Valid CreateProductRequest request){
        return ApiResponse.<Product>builder()
                .data(productService.createProduct(request))
                .build();
    }


    @PostMapping("/update-product/{productId}")
    ApiResponse<ProductUpdateResponse> updateProdudct(@PathVariable String productId, @RequestBody @Valid ProductUpdateRequest request){
        return ApiResponse.<ProductUpdateResponse>builder()
                .data(productService.updateProduct(productId, request))
                .build();
    }

    @DeleteMapping("/delete-product/{productId}")

    ApiResponse<String> deleteProduct(@PathVariable String productId){
        productService.deleteProduct(productId);
        return ApiResponse.<String>builder()
                .data("Product has been deleted")
                .build();
    }


}
