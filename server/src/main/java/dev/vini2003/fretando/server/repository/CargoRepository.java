package dev.vini2003.fretando.server.repository;

import dev.vini2003.fretando.common.entity.Cargo;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends ListCrudRepository<Cargo, Long> {
}
