package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springboot.models.ProductModel;
import org.springframework.stereotype.Repository;

/**
 * Interface de repositório para gerenciar entidades de Produtos.
 */

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, UUID> {
}
