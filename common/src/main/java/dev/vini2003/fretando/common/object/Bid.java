package dev.vini2003.fretando.common.object;

import dev.vini2003.fretando.common.object.base.AbstractObject;

import java.io.Serializable;
import java.util.Currency;
import java.util.Objects;
import java.util.UUID;

public class Bid extends AbstractObject implements Serializable {
    private Request request;

    private Currency currency;
    private double currencyAmount;

    public Bid() {

    }

    public Bid(Request request, Currency currency, double currencyAmount) {
        this.request = request;
        this.currency = currency;
        this.currencyAmount = currencyAmount;
    }

    public Bid(UUID uuid, Request request, Currency currency, double currencyAmount) {
        super(uuid);
        this.request = request;
        this.currency = currency;
        this.currencyAmount = currencyAmount;
    }

    public Request getRequest() {
        return request;
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getCurrencyAmount() {
        return currencyAmount;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setCurrencyAmount(double currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bid bid = (Bid) o;

        if (Double.compare(bid.currencyAmount, currencyAmount) != 0) return false;
        if (!Objects.equals(request, bid.request)) return false;
        return Objects.equals(currency, bid.currency);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = request != null ? request.hashCode() : 0;
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        temp = Double.doubleToLongBits(currencyAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return request + " " + currency + " " + currencyAmount;
    }
}
