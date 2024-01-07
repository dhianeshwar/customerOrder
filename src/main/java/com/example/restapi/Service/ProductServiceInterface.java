package com.example.restapi.Service;

import com.example.restapi.DTO.ProductResponse;
import com.example.restapi.Model.Product;
import java.util.List;
public interface ProductServiceInterface {

     Product insertProduct(Product product);
    ProductResponse getProductResponse(Long id);
     Product deleteProduct(Long id);
    List<ProductResponse> getProductByName(String name);
     List<ProductResponse> getAllProducts();

}
