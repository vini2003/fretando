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
public class Bid  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Bid(Long requestId, double amount) {
        this.requestId = requestId;
        this.amount = amount;
    }

    private Long requestId;

    private double amount;

    public boolean isComplete() {
        return requestId != null && amount != 0;
    }
}
