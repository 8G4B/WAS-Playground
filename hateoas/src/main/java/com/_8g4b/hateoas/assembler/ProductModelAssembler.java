package com._8g4b.hateoas.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com._8g4b.hateoas.controller.ProductController;
import com._8g4b.hateoas.entity.Product;
import com._8g4b.hateoas.model.ProductModel;

@Component
public class ProductModelAssembler extends RepresentationModelAssemblerSupport<Product, ProductModel> {

    public ProductModelAssembler() {
        super(ProductController.class, ProductModel.class);
    }

    @Override
    public ProductModel toModel(Product entity) {
        ProductModel model = instantiateModel(entity);

        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        model.setPrice(entity.getPrice());

        model.add(linkTo(methodOn(ProductController.class).getProduct(entity.getId())).withSelfRel());
        model.add(linkTo(methodOn(ProductController.class).getAllProducts()).withRel("products"));

        return model;
    }
}
