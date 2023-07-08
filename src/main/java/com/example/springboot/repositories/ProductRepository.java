package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springboot.models.ProductModel;

/**
 * Interface de repositório para gerenciar entidades de Produtos.
 */

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductModel, UUID> {
}
