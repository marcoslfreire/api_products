package com.example.springboot.models;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Classe que representa um modelo de produto.
 */
@Entity
@Table(name = "TB_PRODUCT")
public class ProductModel extends RepresentationModel<ProductModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID idProduct;
    private String name;
    private BigDecimal value;

    /**
     * Obtém o ID do produto.
     *
     * @return O ID do produto.
     */
    public UUID getIdProduct() {
        return idProduct;
    }

    /**
     * Define o ID do produto.
     *
     * @param idProduct O ID do produto a ser definido.
     */
    public void setIdProduct(UUID idProduct) {
        this.idProduct = idProduct;
    }

    /**
     * Obtém o nome do produto.
     *
     * @return O nome do produto.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do produto.
     *
     * @param name O nome do produto a ser definido.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtém o valor do produto.
     *
     * @return O valor do produto.
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Define o valor do produto.
     *
     * @param value O valor do produto a ser definido.
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getId() {
        return null;
    }
}
