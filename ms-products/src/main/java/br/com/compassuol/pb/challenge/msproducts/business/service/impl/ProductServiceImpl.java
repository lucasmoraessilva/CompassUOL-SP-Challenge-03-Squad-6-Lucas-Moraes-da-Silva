package br.com.compassuol.pb.challenge.msproducts.business.service.impl;

import br.com.compassuol.pb.challenge.msproducts.business.service.ProductService;
import br.com.compassuol.pb.challenge.msproducts.model.entity.ProductModel;
import br.com.compassuol.pb.challenge.msproducts.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductModel> getAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public ProductModel getProductById(String productId) {
        Optional<ProductModel> search = this.productRepository.findById(productId);

        return search.isPresent() ? search.get() : null;
    }

    @Override
    public ProductModel addProduct(ProductModel newProduct) {
        return this.productRepository.save(newProduct);
    }

    @Override
    public ProductModel updateProductById(String productId, ProductModel productToUpgrade) {
        Optional<ProductModel> search = this.productRepository.findById(productId);

        if (search.isPresent()) {
            ProductModel searchedProduct = search.get();

            searchedProduct.setName(productToUpgrade.getName());
            searchedProduct.setDescription(productToUpgrade.getDescription());
            searchedProduct.setDate(productToUpgrade.getDate());
            searchedProduct.setPrice(productToUpgrade.getPrice());
            searchedProduct.setImgUrl(productToUpgrade.getImgUrl());
            searchedProduct.setCategories(productToUpgrade.getCategories());

            return this.productRepository.save(searchedProduct);
        }

        return null;
    }

    @Override
    public Void deleteProductById(String productIdToDelete) {
        this.productRepository.deleteById(productIdToDelete);

        return null;
    }
}
