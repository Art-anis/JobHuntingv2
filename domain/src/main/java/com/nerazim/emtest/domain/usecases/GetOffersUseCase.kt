package com.nerazim.emtest.domain.usecases

import com.nerazim.emtest.domain.models.Offer
import com.nerazim.emtest.domain.repository.DataRepository

class GetOffersUseCase(private val repository: DataRepository) {

    fun execute(): List<Offer> {
        return repository.getOffers()
    }
}