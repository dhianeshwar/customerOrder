package com.example.restapi.ServiceImpl;
import com.example.restapi.DTO.ProductResponse;
import com.example.restapi.Error.NoProductFoundException;
import com.example.restapi.Error.ProductAlreadyExist;
import com.example.restapi.Model.Product;
import com.example.restapi.Repository.ProductRepository;
import com.example.restapi.Service.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductImpl implements ProductServiceInterface {
@Autowired
private ProductRepository productRepository;

     public Product insertProduct(Product product)
     {
            if(productRepository.findById(product.getProductId()).isPresent())
               throw new ProductAlreadyExist("A product is already present with the provided Id");
            return productRepository.save(product);
     }
     private Product getProduct(Long id)
     {
         return productRepository.findById(id).orElseThrow(() -> new NoProductFoundException("There is no product available with the given id "));
     }

     public ProductResponse getProductResponse(Long id)
     {
         return productToProductResponse(getProduct(id));
     }
     public Product deleteProduct(Long id)
     {
         Product product=getProduct(id);
         product.setIsAvailable(0);
         return productRepository.save(product);
     }
     public List<ProductResponse> getAllProducts()
     {
         return productRepository.findAll().stream().map(this::productToProductResponse).toList();
     }
     public List<ProductResponse> getProductByName(String name)
     {
         return productRepository.findByName(name).stream().map(this::productToProductResponse).toList();
     }

     public  ProductResponse productToProductResponse(Product product)
     {
          return new ProductResponse(product.getProductId(),product.getName(),product.getPrize());
     }

     public List<ProductResponse> findProductLessThan(int prize)
     {
         return Optional.of(productRepository.findByPrizeLessThan(prize))
                 .filter(product->!product.isEmpty())
                 .orElseThrow(()->new NoProductFoundException("No product with the given amount had been found"))
                 .stream()
                 .map(this::productToProductResponse)
                 .toList();
     }
}
