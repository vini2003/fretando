package dev.vini2003.fretando.client.repository

import dev.vini2003.fretando.common.entity.Cargo

object RemoteCargoRepository : RemoteRepository<Cargo>("http://localhost:8080/api/cargo") {
}