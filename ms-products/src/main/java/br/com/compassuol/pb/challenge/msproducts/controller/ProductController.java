package br.com.compassuol.pb.challenge.msproducts.controller;

import br.com.compassuol.pb.challenge.msproducts.business.service.ProductService;
import br.com.compassuol.pb.challenge.msproducts.model.entity.ProductModel;
import br.com.compassuol.pb.challenge.msproducts.repository.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        return ResponseEntity.ok(this.productService.getAllProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable("productId") String productId) {
        return ResponseEntity.ok(this.productService.getProductById(productId));
    }

    @PostMapping
    public ResponseEntity<ProductModel> addProduct(@RequestBody ProductModel newProduct, HttpServletRequest request) throws URISyntaxException {
        ProductModel addedProduct = this.productService.addProduct(newProduct);

        return ResponseEntity.created(new URI(request.getRequestURI() + addedProduct.getId())).body(addedProduct);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductModel> updateProductById(@PathVariable("productId") String productId, @RequestBody ProductModel productToUpdate) {
        ProductModel productUpdated = this.productService.updateProductById(productId, productToUpdate);

        if (productUpdated != null) {
            return ResponseEntity.ok(productUpdated);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity deleteProductById(@PathVariable("productId") String productId) {
        this.productService.deleteProductById(productId);

        return ResponseEntity.ok(null);
    }
}