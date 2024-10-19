package com.nerazim.emtest.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nerazim.emtest.R
import com.nerazim.emtest.cards.VacancyComponent
import com.nerazim.emtest.viewmodels.FavoritesViewModel
import org.koin.androidx.compose.koinViewModel

//экран "Избранное"
@Composable
fun FavoritesScreen() {
    //viewmodel
    val viewModel = koinViewModel<FavoritesViewModel>()

    //список вакансий в "Избранном"
    val favorites by viewModel.getFavoritesListLive().observeAsState(listOf())

    //поверхность
    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)
    ) {
        Column {
            Spacer(modifier = Modifier.height(64.dp))
            //заголовок страницы
            Text(
                text = stringResource(R.string.favorite),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(24.dp))
            //получить склонение вакансий
            val declension = getVacancyDeclension(favorites.size)
            //текст с количеством вакансий
            Text(
                text = "${favorites.size} $declension",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF858688)
            )
            Spacer(modifier = Modifier.height(16.dp))
            //столбец с вакансиями
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(favorites) { favoriteVacancy ->
                    VacancyComponent(
                        vacancy = favoriteVacancy,
                        removeFromFavorites = viewModel::removeFromFavorites
                    )
                }
            }
        }
    }
}