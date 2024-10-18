package com.nerazim.emtest.domain

class GetOffersUseCase(private val repository: DataRepository) {

    fun execute(): List<Offer> {
        return repository.getOffers()
    }
}