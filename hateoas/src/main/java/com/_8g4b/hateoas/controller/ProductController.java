package com._8g4b.hateoas.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com._8g4b.hateoas.assembler.ProductModelAssembler;
import com._8g4b.hateoas.entity.Product;
import com._8g4b.hateoas.model.ProductModel;
import com._8g4b.hateoas.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductModelAssembler productModelAssembler;

    @GetMapping
    public CollectionModel<ProductModel> getAllProducts() {
        var products = productRepository.findAll();
        var productModels = productModelAssembler.toCollectionModel(products);

        Link selfLink = linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel();
        productModels.add(selfLink);

        return productModels;
    }

    @GetMapping("/{id}")
    public ProductModel getProduct(@PathVariable Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return productModelAssembler.toModel(product);
    }

    @PostMapping
    public ProductModel createProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return productModelAssembler.toModel(savedProduct);
    }

    @PutMapping("/{id}")
    public ProductModel updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());

        Product updatedProduct = productRepository.save(existingProduct);
        return productModelAssembler.toModel(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
