package dev.vini2003.fretando.client.repository

import dev.vini2003.fretando.client.properties.Properties
import dev.vini2003.fretando.common.entity.Request

val RemoteRequestRepository = RemoteRepository<Request> { Properties.requestRepositoryUrl }