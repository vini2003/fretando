package dev.vini2003.fretando.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Cargo(double length, DimensionUnit lengthUnit, double width, DimensionUnit widthUnit, double height, DimensionUnit heightUnit, double weight, WeightUnit weightUnit, String description) {
        this.length = length;
        this.lengthUnit = lengthUnit;
        this.width = width;
        this.widthUnit = widthUnit;
        this.height = height;
        this.heightUnit = heightUnit;
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.description = description;
    }

    private double length;
    private DimensionUnit lengthUnit;

    private double width;
    private DimensionUnit widthUnit;

    private double height;
    private DimensionUnit heightUnit;

    private double weight;
    private WeightUnit weightUnit;

    private String description;

    public boolean isComplete() {
        return length != 0 && lengthUnit != null && width != 0 && widthUnit != null && height != 0 && heightUnit != null && weight != 0 && weightUnit != null && description != null;
    }

    @Override
    public String toString() {
        return "[ Cargo ID: " + id +
                ", Length: " + length + " " + lengthUnit +
                ", Width: " + width + " " + widthUnit +
                ", Height: " + height + " " + heightUnit +
                ", Weight: " + weight + " " + weightUnit +
                ", Description: " + description +
                " ]";
    }

    public enum DimensionUnit {
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
