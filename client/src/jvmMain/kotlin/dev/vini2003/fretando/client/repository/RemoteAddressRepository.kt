package dev.vini2003.fretando.client.repository

import dev.vini2003.fretando.common.entity.Address

val RemoteAddressRepository = RemoteRepository<Address>("http://localhost:8080/api/address")