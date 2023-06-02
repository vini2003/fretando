package dev.vini2003.fretando.common.object;

import dev.vini2003.fretando.common.object.base.AbstractObject;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Cargo extends AbstractObject implements Serializable {
    private double length;
    private double width;
    private double height;
    private double weight;
    private String description;

    public Cargo() {

    }

    public Cargo(double length, double width, double height, double weight, String description) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.description = description;
    }

    public Cargo(UUID uuid, double length, double width, double height, double weight, String description) {
        super(uuid);
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.description = description;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String getDescription() {
        return description;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
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
        return Objects.equals(description, cargo.description);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(length);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(width);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(height);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return length + ", " + width + ", " + height + ", " + weight + ", " + description;
    }
}
