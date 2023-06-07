package dev.vini2003.fretando.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double length;
    private LengthUnit lengthUnit;

    private double width;
    private LengthUnit widthUnit;

    private double height;
    private LengthUnit heightUnit;

    private double weight;
    private WeightUnit weightUnit;

    private String description;

    public enum LengthUnit {
        METERS, FEET;

        public String asString() {
            switch (this) {
                case METERS:
                    return "m";
                case FEET:
                    return "ft";
            }

            return "";
        }
    }

    public enum WeightUnit {
        KILOGRAMS, POUNDS;

        public String asString() {
            switch (this) {
                case KILOGRAMS:
                    return "kg";
                case POUNDS:
                    return "lb";
            }

            return "";
        }
    }
}
