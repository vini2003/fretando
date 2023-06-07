package dev.vini2003.fretando.common.object;

import dev.vini2003.fretando.common.object.base.AbstractObject;

import java.io.Serializable;
import java.util.Currency;
import java.util.Objects;
import java.util.UUID;

public class Bid extends AbstractObject implements Serializable {
    private UUID requestId;
    private double amount;

    public Bid() {

    }

    public Bid(UUID requestId, Currency currency, double amount) {
        this.requestId = requestId;
        this.amount = amount;
    }

    public Bid(UUID uuid, UUID requestId, Currency currency, double amount) {
        super(uuid);
        this.requestId = requestId;
        this.amount = amount;
    }

    public UUID getRequestId() {
        return requestId;
    }

    public double getAmount() {
        return amount;
    }

    public void setRequestId(UUID requestId) {
        this.requestId = requestId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bid bid = (Bid) o;

        if (Double.compare(bid.amount, amount) != 0) return false;
        return Objects.equals(requestId, bid.requestId);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = requestId != null ? requestId.hashCode() : 0;
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return " " + requestId + " " + amount;
    }
}
