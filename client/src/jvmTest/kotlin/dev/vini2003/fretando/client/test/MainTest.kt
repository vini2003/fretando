package dev.vini2003.fretando.client.test

import dev.vini2003.fretando.client.repository.RemoteRepository
import dev.vini2003.fretando.common.entity.Address
import dev.vini2003.fretando.common.entity.Bid
import dev.vini2003.fretando.common.entity.Cargo
import dev.vini2003.fretando.common.entity.Request
import dev.vini2003.fretando.common.util.Mock
import io.ktor.client.call.*
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

suspend fun main() {
    val addressRepository = RemoteRepository<Request>("http://localhost:8080/api/request")

    List(50) {
        // Create mock addresses
        Mock.request()
    }.map { mockAddress ->
        // Test insertion
        val saveResponse = addressRepository.save(mockAddress)
        assertEquals(200, saveResponse.status.value)

        val new = saveResponse.body<Address>()
        new
    }
}


class MainTest {
    private var addressRepository = RemoteRepository<Address>("http://localhost:8080/api/address")
    private var bidRepository = RemoteRepository<Bid>("http://localhost:8080/api/bid")
    private var cargoRepository = RemoteRepository<Cargo>("http://localhost:8080/api/cargo")
    private var requestRepository = RemoteRepository<Request>("http://localhost:8080/api/request")


    @Test
    fun `Test Address`() {
        runBlocking {
            List(10) {
                // Create mock addresses
                Mock.address()
            }.map { mockAddress ->
                // Test insertion
                val saveResponse = addressRepository.save(mockAddress)
                assertEquals(200, saveResponse.status.value)

                val new = saveResponse.body<Address>()
                new
            }.also {
                // Test group retrieval, ensure the data matches
                val findAllResponse = addressRepository.findAll()
                assert(findAllResponse.containsAll(it))
            }.onEach {
                // Test independent retrieval, ensure the data matches
                val findByIdResponse = addressRepository.findById(it.id)
                assertEquals(findByIdResponse, it)
            }.onEach {
                val mock = Mock.address().let { mock ->
                    Address(
                        it.id,
                        mock.street,
                        mock.number,
                        mock.city,
                        mock.state,
                        mock.postalCode,
                        mock.country,
                        mock.notes
                    )
                }

                // Test updating
                val updateResponse = addressRepository.update(it.id, mock)
                assertEquals(200, updateResponse.status.value)

                // Ensure the data matches
                val findByIdResponse = addressRepository.findById(it.id)
                assertEquals(findByIdResponse, mock)
            }.onEach { mockAddress ->
                // Test deletion
                val deleteByIdResponse = addressRepository.deleteById(mockAddress.id)
                assertEquals(200, deleteByIdResponse.status.value)
            }.also {
                // Ensure the data is empty
                val findAllResponse = addressRepository.findAll()
                assert(!findAllResponse.containsAll(it))
            }
        }
    }

    @Test
    fun `Test Bid`() {
        runBlocking {
            List(10) {
                // Create mock bids
                Mock.bid()
            }.map { mockBid ->
                // Test insertion
                val saveResponse = bidRepository.save(mockBid)
                assertEquals(200, saveResponse.status.value)

                saveResponse.body<Bid>()
            }.also {
                // Test group retrieval, ensure the data matches
                val findAllResponse = bidRepository.findAll()
                assert(findAllResponse.containsAll(it))
            }.onEach {
                // Test independent retrieval, ensure the data matches
                val findByIdResponse = bidRepository.findById(it.id)
                assertEquals(findByIdResponse, it)
            }.onEach {
                val mock = Mock.bid().let { mock ->
                    Bid(it.id, mock.requestId, mock.amount)
                }

                // Test updating
                val updateResponse = bidRepository.update(it.id, mock)
                assertEquals(200, updateResponse.status.value)

                // Ensure the data matches
                val findByIdResponse = bidRepository.findById(it.id)
                assertEquals(findByIdResponse, mock)
            }.onEach { mockBid ->
                // Test deletion
                val deleteByIdResponse = bidRepository.deleteById(mockBid.id)
                assertEquals(200, deleteByIdResponse.status.value)
            }.also {
                // Ensure the data is empty
                val findAllResponse = bidRepository.findAll()
                assert(!findAllResponse.containsAll(it))
            }
        }
    }

    @Test
    fun `Test Cargo`() {
        runBlocking {
            List(10) {
                // Create mock cargos
                Mock.cargo()
            }.map { mockCargo ->
                // Test insertion
                val saveResponse = cargoRepository.save(mockCargo)
                assertEquals(200, saveResponse.status.value)

                saveResponse.body<Cargo>()
            }.also {
                // Test group retrieval, ensure the data matches
                val findAllResponse = cargoRepository.findAll()
                assert(findAllResponse.containsAll(it))
            }.onEach {
                // Test independent retrieval, ensure the data matches
                val findByIdResponse = cargoRepository.findById(it.id)
                assertEquals(findByIdResponse, it)
            }.onEach {
                val mock = Mock.cargo().let { mock ->
                    Cargo(
                        it.id,
                        mock.length,
                        mock.lengthUnit,
                        mock.width,
                        mock.widthUnit,
                        mock.height,
                        mock.heightUnit,
                        mock.weight,
                        mock.weightUnit,
                        mock.description
                    )
                }

                // Test updating
                val updateResponse = cargoRepository.update(it.id, mock)
                assertEquals(200, updateResponse.status.value)

                // Ensure the data matches
                val findByIdResponse = cargoRepository.findById(it.id)
                assertEquals(findByIdResponse, mock)
            }.onEach { mockCargo ->
                // Test deletion
                val deleteByIdResponse = cargoRepository.deleteById(mockCargo.id)
                assertEquals(200, deleteByIdResponse.status.value)
            }.also {
                // Ensure the data is empty
                val findAllResponse = cargoRepository.findAll()
                assert(!findAllResponse.containsAll(it))
            }
        }
    }

    @Test
    fun `Test Request`() {
        runBlocking {
            List(10) {
                // Create mock requests
                Mock.request()
            }.map { mockRequest ->
                // Test insertion
                val saveResponse = requestRepository.save(mockRequest)
                assertEquals(200, saveResponse.status.value)

                saveResponse.body<Request>()
            }.also {
                // Test group retrieval, ensure the data matches
                val findAllResponse = requestRepository.findAll()
                assert(findAllResponse.containsAll(it))
            }.onEach {
                // Test independent retrieval, ensure the data matches
                val findByIdResponse = requestRepository.findById(it.id)
                assertEquals(findByIdResponse, it)
            }.onEach {
                val mock = Mock.request().let { mock ->
                    Request(it.id,
                        mock.origin.let { mockAddress ->
                            Address(
                                it.origin.id,
                                mockAddress.street,
                                mockAddress.number,
                                mockAddress.city,
                                mockAddress.state,
                                mockAddress.postalCode,
                                mockAddress.country,
                                mockAddress.notes
                            )
                        },
                        mock.destination.let { mockAddress ->
                            Address(
                                it.destination.id,
                                mockAddress.street,
                                mockAddress.number,
                                mockAddress.city,
                                mockAddress.state,
                                mockAddress.postalCode,
                                mockAddress.country,
                                mockAddress.notes
                            )
                        },
                        mock.cargo.let { mockCargo ->
                            Cargo(
                                it.cargo.id,
                                mockCargo.length,
                                mockCargo.lengthUnit,
                                mockCargo.width,
                                mockCargo.widthUnit,
                                mockCargo.height,
                                mockCargo.heightUnit,
                                mockCargo.weight,
                                mockCargo.weightUnit,
                                mockCargo.description
                            )
                        },
                        mock.bids.mapIndexed { index, mockBid ->
                            Bid(
                                it.bids[index].id,
                                mockBid.requestId,
                                mockBid.amount
                            )
                        })
                }

                // Test updating
                val updateResponse = requestRepository.update(it.id, mock)
                assertEquals(200, updateResponse.status.value)

                // Ensure the data matches
                val findByIdResponse = requestRepository.findById(it.id)
                assertEquals(findByIdResponse, mock)
            }.onEach { mockRequest ->
                // Test deletion
                val deleteByIdResponse = requestRepository.deleteById(mockRequest.id)
                assertEquals(200, deleteByIdResponse.status.value)
            }.also {
                // Ensure the data is empty
                val findAllResponse = requestRepository.findAll()
                assert(!findAllResponse.containsAll(it))
            }
        }
    }
}