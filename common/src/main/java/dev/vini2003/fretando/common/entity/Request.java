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

    @OneToOne(cascade = CascadeType.ALL)
    private Address origin;
    @OneToOne(cascade = CascadeType.ALL)
    private Address destination;
    @OneToOne(cascade = CascadeType.ALL)
    private Cargo cargo;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Bid> bids;
}
