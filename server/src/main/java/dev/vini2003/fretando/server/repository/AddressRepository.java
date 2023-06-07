package dev.vini2003.fretando.server.repository;

import dev.vini2003.fretando.common.entity.Address;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends ListCrudRepository<Address, Long> {
}
