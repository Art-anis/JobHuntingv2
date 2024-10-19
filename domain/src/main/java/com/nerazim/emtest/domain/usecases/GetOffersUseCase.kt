package com.nerazim.emtest.domain.usecases

import com.nerazim.emtest.domain.models.Offer
import com.nerazim.emtest.domain.repository.DataRepository

//usecase получения рекомендаций
class GetOffersUseCase(private val repository: DataRepository) {
    //исполнение
    fun execute(): List<Offer> {
        return repository.getOffers()
    }
}