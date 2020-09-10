package com.testtask.caloric.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Сущность заявки на изменение атрибутов продукта
 */
@Data
@Entity
@Table(name = "product_update_order")
public
class ProductUpdateOrder {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    // идентификатор продукта. НЕ СТАЛ делать сслыкой на таблицу продуктов, чтобы не городить лишнего кода и проверок.
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "name")
    private String name;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "calories")
    private double calories;

    @Column(name = "proteins")
    private double proteins;

    @Column(name = "fats")
    private double fats;

    @Column(name = "carbohydrates")
    private double carbohydrates;

    @Column(name = "update_date")
    private Date updateDate   = new Date();

    // флаг для администратора - обработан запрос или нет
    @Column(name = "is_processed")
    private boolean isProcessed = false;

    public ProductUpdateOrder() {
    }

    public ProductUpdateOrder(Long productId, String name, String manufacturer, double calories, double proteins, double fats, double carbohydrates) {
        this.productId = productId;
        this.name = name;
        this.manufacturer = manufacturer;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }
}