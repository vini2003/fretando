package dev.vini2003.fretando.client.properties

import dev.vini2003.fretando.client.properties.misc.property

object Properties {
    var addressRepositoryUrl by property("addressRepositoryUrl", "http://localhost:8080/api/address")
    var bidRepositoryUrl by property("bidRepositoryUrl", "http://localhost:8080/api/bid")
    var cargoRepositoryUrl by property("cargoRepositoryUrl", "http://localhost:8080/api/cargo")
    var requestRepositoryUrl by property("requestRepositoryUrl", "http://localhost:8080/api/request")
}