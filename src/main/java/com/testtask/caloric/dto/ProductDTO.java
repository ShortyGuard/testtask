package com.testtask.caloric.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public enum ProductDTO {
    ;

    private interface Id {
        @Positive Long getId();
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

    private interface IsAviable {
        boolean isAviable();
    }

    public enum RequestProduct {
        ;

        @Getter
        @Setter
        @NoArgsConstructor
        public static class Create implements Name, Manufacturer, Calories, Proteins, Fats, Carbohydrates {
            String name;
            String manufacturer;
            Double calories;
            Double proteins;
            Double fats;
            double carbohydrates;
        }
    }

    public enum ResponseProduct {
        ;

        @Getter
        @Setter
        @NoArgsConstructor
        public static class Basic implements Id, Name, Manufacturer {
            Long id;
            String name;
            String manufacturer;

        }

        @Getter
        @Setter
        @NoArgsConstructor
        public static class Public implements Id, Name, Manufacturer, Calories, Proteins, Fats, Carbohydrates {
            Long id;
            String name;
            String manufacturer;
            Double calories;
            Double proteins;
            Double fats;
            double carbohydrates;
        }

        @Getter
        @Setter
        @NoArgsConstructor
        public static class Private implements Id, Name, Manufacturer, Calories, Proteins, Fats, Carbohydrates, IsAviable {
            Long id;
            String name;
            String manufacturer;
            Double calories;
            Double proteins;
            Double fats;
            double carbohydrates;
            boolean isAviable;
        }
    }
}
