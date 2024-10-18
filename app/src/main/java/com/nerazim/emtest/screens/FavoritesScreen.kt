package com.nerazim.emtest.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.nerazim.emtest.cards.VacancyComponent
import com.nerazim.emtest.viewmodels.FavoritesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoritesScreen() {
    val viewModel = koinViewModel<FavoritesViewModel>()

    val favorites by viewModel.getFavoritesListLive().observeAsState(listOf())

    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(favorites) { favoriteVacancy ->
                VacancyComponent(
                    vacancy = favoriteVacancy,
                    removeFromFavorites = viewModel::removeFromFavorites
                )
            }
        }
    }
}