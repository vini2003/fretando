package dev.vini2003.fretando.server.repository;

import dev.vini2003.fretando.common.entity.Request;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends ListCrudRepository<Request, Long> {
}
