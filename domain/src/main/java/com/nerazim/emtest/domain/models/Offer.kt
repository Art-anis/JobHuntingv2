package com.nerazim.emtest.domain.models

//рекомендации
data class Offer(
    val id: String? = null, //идентификатор
    val title: String? = null, //название
    val button: OfferButton? = null, //кнопка
    val link: String? = null //ссылка
)

//кнопка в блоке рекомендации
data class OfferButton(
    val text: String
)