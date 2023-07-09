package com.example.springboot.controllers;

import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Controlador da API de Produtos.
 */
@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    /**
     * Cria um novo produto.
     *
     * @param productRecordDto O DTO de registro do produto. Deve conter os campos "name" (String) e "value" (BigDecimal).
     * @return A resposta HTTP com o produto criado.
     */
    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    /**
     * Obtém todos os produtos cadastrados.
     *
     * @return A resposta HTTP com a lista de produtos.
     */
    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        List<ProductModel> productsList = productRepository.findAll();
        if (!productsList.isEmpty()) {
            for (ProductModel product : productsList) {
                UUID id = product.getIdProduct();
                product.add(linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
    }

    /**
     * Obtém um produto específico pelo ID.
     *
     * @param id O ID do produto a ser obtido.
     * @return A resposta HTTP com os detalhes do produto.
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id) {
        Optional<ProductModel> productO = productRepository.findById(id);
        if (productO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não cadastrado na base de dados!");
        }
        productO.get().add(linkTo(methodOn(ProductController.class).getAllProducts()).withRel("Lista de Produtos!"));
        return ResponseEntity.status(HttpStatus.OK).body(productO.get());
    }

    /**
     * Atualiza os detalhes de um produto existente.
     *
     * @param id                O ID do produto a ser atualizado.
     * @param productRecordDto  O DTO de registro do produto atualizado. Deve conter os campos "name" (String) e "value" (BigDecimal).
     * @return A resposta HTTP com o produto atualizado.
     */
    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid ProductRecordDto productRecordDto) {
        Optional<ProductModel> productO = productRepository.findById(id);
        if (productO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não cadastrado na base de dados!");
        }
        var produtoModel = productO.get();
        BeanUtils.copyProperties(productRecordDto, produtoModel);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(produtoModel));
    }

    /**
     * Exclui um produto existente.
     *
     * @param id O ID do produto a ser excluído.
     * @return A resposta HTTP com a mensagem de sucesso.
     */
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id) {
        Optional<ProductModel> productO = productRepository.findById(id);
        if (productO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não cadastrado na base de dados!");
        }
        productRepository.delete(productO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso!");
    }
}
