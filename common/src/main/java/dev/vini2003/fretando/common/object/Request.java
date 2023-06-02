package dev.vini2003.fretando.common.object;

import dev.vini2003.fretando.common.object.base.AbstractObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Request extends AbstractObject implements Serializable {
    private Address origin;
    private Address destination;
    private Cargo cargo;
    private List<Bid> bids;

    public Request() {
        this.bids = new ArrayList<>();
    }

    public Request(Address origin, Address destination, Cargo cargo) {
        this.origin = origin;
        this.destination = destination;
        this.cargo = cargo;
        this.bids = new ArrayList<>();
    }

    public Request(UUID uuid, Address origin, Address destination, Cargo cargo, List<Bid> bids) {
        super(uuid);
        this.origin = origin;
        this.destination = destination;
        this.cargo = cargo;
        this.bids = bids;
    }

    public Address getOrigin() {
        return origin;
    }

    public Address getDestination() {
        return destination;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void addBid(Bid bid) {
        bids.add(bid);
    }

    public void setOrigin(Address origin) {
        this.origin = origin;
    }

    public void setDestination(Address destination) {
        this.destination = destination;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (!Objects.equals(origin, request.origin)) return false;
        if (!Objects.equals(destination, request.destination)) return false;
        if (!Objects.equals(cargo, request.cargo)) return false;
        return Objects.equals(bids, request.bids);
    }

    @Override
    public int hashCode() {
        int result = origin != null ? origin.hashCode() : 0;
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (cargo != null ? cargo.hashCode() : 0);
        result = 31 * result + (bids != null ? bids.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return origin + " -> " + destination + " (" + cargo + ")" + " (" + bids.size() + " bids)";
    }
}
