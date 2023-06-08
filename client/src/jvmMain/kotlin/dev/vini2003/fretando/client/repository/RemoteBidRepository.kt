package dev.vini2003.fretando.client.repository

import dev.vini2003.fretando.common.entity.Bid

val RemoteBidRepository = RemoteRepository<Bid>("http://localhost:8080/api/bid")