package br.com.compassuol.pb.challenge.msproducts.business.service;

import br.com.compassuol.pb.challenge.msproducts.model.entity.ProductModel;
import br.com.compassuol.pb.challenge.msproducts.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {

    public List<ProductModel> getAllProducts();
    public ProductModel getProductById(String productId);
    public ProductModel addProduct(ProductModel newProduct);
    public ProductModel updateProductById(String productId, ProductModel productToUpgrade);
    public Void deleteProductById(String productIdToDelete);
}
