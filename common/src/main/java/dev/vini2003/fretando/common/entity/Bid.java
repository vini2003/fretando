package dev.vini2003.fretando.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bid  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Bid(Request request, double amount) {
        this.request = request;
        this.amount = amount;
    }

    @ManyToOne
    private Request request;

    private double amount;

    public boolean isComplete() {
        return request != null && amount != 0;
    }
}
