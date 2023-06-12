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
