package dev.vini2003.fretando.server.repository;

import dev.vini2003.fretando.common.entity.Bid;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends ListCrudRepository<Bid, Long> {
}
