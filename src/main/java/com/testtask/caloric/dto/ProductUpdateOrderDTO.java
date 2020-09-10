package com.testtask.caloric.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public enum ProductUpdateOrderDTO {
    ;

    private interface Id {
        @Positive Long getId();
    }

    private interface ProductId {
        @Positive Long getProductId();
    }

    private interface Name {
        @NotBlank String getName();
    }

    private interface Manufacturer {
        @NotBlank String getManufacturer();
    }

    private interface Calories {
        @PositiveOrZero Double getCalories();
    }

    private interface Proteins {
        @PositiveOrZero Double getProteins();
    }

    private interface Fats {
        @PositiveOrZero Double getFats();
    }

    private interface Carbohydrates {
        @PositiveOrZero double getCarbohydrates();
    }

    private interface IsProcessed {
        boolean isProcessed();
    }

    public enum RequestProductUpdateOrder {
        ;

        @Getter
        @Setter
        @NoArgsConstructor
        public static class Create implements ProductId, Name, Manufacturer, Calories, Proteins, Fats, Carbohydrates {
            Long productId;
            String name;
            String manufacturer;
            Double calories;
            Double proteins;
            Double fats;
            double carbohydrates;
        }
    }

    public enum ResponseProductUpdateOrder {
        ;


        @Getter
        @Setter
        @NoArgsConstructor
        public static class Private implements Id, ProductId, Name, Manufacturer, Calories, Proteins, Fats, Carbohydrates, IsProcessed {
            Long id;
            Long productId;
            String name;
            String manufacturer;
            Double calories;
            Double proteins;
            Double fats;
            double carbohydrates;
            boolean isProcessed;
        }
    }
}
