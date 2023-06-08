package dev.vini2003.fretando.client.repository

import dev.vini2003.fretando.common.entity.Request

val RemoteRequestRepository = RemoteRepository<Request>("http://localhost:8080/api/request")