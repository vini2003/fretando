package dev.vini2003.fretando.common.object;

import dev.vini2003.fretando.common.object.base.AbstractObject;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Cargo extends AbstractObject implements Serializable {
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

    private double length;
    private LengthUnit lengthUnit;

    private double width;
    private LengthUnit widthUnit;

    private double height;
    private LengthUnit heightUnit;

    private double weight;
    private WeightUnit weightUnit;

    private String description;

    public Cargo() {

    }

    // modify all constructors to include units

    public Cargo(double length, LengthUnit lengthUnit, double width, LengthUnit widthUnit,
                 double height, LengthUnit heightUnit, double weight, WeightUnit weightUnit,
                 String description) {
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

    public Cargo(UUID uuid, double length, LengthUnit lengthUnit, double width, LengthUnit widthUnit,
                 double height, LengthUnit heightUnit, double weight, WeightUnit weightUnit,
                 String description) {
        super(uuid);
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

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public LengthUnit getLengthUnit() {
        return lengthUnit;
    }

    public void setLengthUnit(LengthUnit lengthUnit) {
        this.lengthUnit = lengthUnit;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public LengthUnit getWidthUnit() {
        return widthUnit;
    }

    public void setWidthUnit(LengthUnit widthUnit) {
        this.widthUnit = widthUnit;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public LengthUnit getHeightUnit() {
        return heightUnit;
    }

    public void setHeightUnit(LengthUnit heightUnit) {
        this.heightUnit = heightUnit;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public WeightUnit getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(WeightUnit weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cargo cargo = (Cargo) o;

        if (Double.compare(cargo.length, length) != 0) return false;
        if (Double.compare(cargo.width, width) != 0) return false;
        if (Double.compare(cargo.height, height) != 0) return false;
        if (Double.compare(cargo.weight, weight) != 0) return false;
        if (lengthUnit != cargo.lengthUnit) return false;
        if (widthUnit != cargo.widthUnit) return false;
        if (heightUnit != cargo.heightUnit) return false;
        if (weightUnit != cargo.weightUnit) return false;
        return Objects.equals(description, cargo.description);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(length);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (lengthUnit != null ? lengthUnit.hashCode() : 0);
        temp = Double.doubleToLongBits(width);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (widthUnit != null ? widthUnit.hashCode() : 0);
        temp = Double.doubleToLongBits(height);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (heightUnit != null ? heightUnit.hashCode() : 0);
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (weightUnit != null ? weightUnit.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Cargo Details:\n" +
                "Length: " + length + " " + lengthUnit + "\n" +
                "Width: " + width + " " + widthUnit + "\n" +
                "Height: " + height + " " + heightUnit + "\n" +
                "Weight: " + weight + " " + weightUnit + "\n" +
                "Description: " + description + "\n";
    }
}
