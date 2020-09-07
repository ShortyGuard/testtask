package com.testtask.caloric;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Сущность заявки на изменение атрибутов продукта
 */
@Data
@Entity
class ProductUpdate {

    @Id
    @GeneratedValue
    private Long id;
    // идентификатор продукта. НЕ СТАЛ делать сслыкой на таблицу продуктов, чтобы не городить лишнего кода и проверок.
    private Long productId;
    private String name;
    private String manufacturer;
    private double calories;
    private double proteins;
    private double fats;
    private double carbohydrates;
    private Date updateDate   = new Date();
    // флаг для администратора - обработан запрос или нет
    private boolean isProcessed = false;

    public ProductUpdate() {
    }

    public ProductUpdate(Long productId, String name, String manufacturer, double calories, double proteins, double fats, double carbohydrates, Date updateDate, boolean isProcessed) {
        this.productId  = productId;
        this.name = name;
        this.manufacturer = manufacturer;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.updateDate = updateDate;
        this.isProcessed = isProcessed;
    }
}