package dev.vini2003.fretando.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Request(Address origin, Address destination, Cargo cargo) {
        this.origin = origin;
        this.destination = destination;
        this.cargo = cargo;
    }

    @OneToOne(cascade = CascadeType.ALL)
    private Address origin;
    @OneToOne(cascade = CascadeType.ALL)
    private Address destination;
    @OneToOne(cascade = CascadeType.ALL)
    private Cargo cargo;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Bid> bids;

    public boolean isComplete() {
        return origin != null && destination != null && cargo != null;
    }

    @Override
    public String toString() {
        return "[ Request ID: " + id +
                ", Origin: " + origin +
                ", Destination: " + destination +
                ", Cargo: " + cargo +
                ", Bids: " + bids +
                " ]";
    }
}
