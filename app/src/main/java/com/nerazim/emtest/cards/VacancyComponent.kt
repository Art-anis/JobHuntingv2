package com.nerazim.emtest.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nerazim.emtest.R
import com.nerazim.emtest.domain.Vacancy
import com.nerazim.emtest.viewmodels.FavoriteNumberViewModel
import com.nerazim.emtest.viewmodels.ViewModelFactory

@Composable
fun VacancyComponent(
    vacancy: com.nerazim.emtest.domain.Vacancy,
    addToFavorites: (com.nerazim.emtest.domain.Vacancy) -> Unit = {},
    removeFromFavorites: (com.nerazim.emtest.domain.Vacancy) -> Unit
) {
    val favoriteNumberViewModel: FavoriteNumberViewModel = viewModel(factory = ViewModelFactory.Factory)

    Surface(
        color = Color(0xFF222325),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {

    }) {
        Row {
            Column(modifier =
            Modifier
                .width(219.dp)
                .padding(top = 16.dp, start = 16.dp)
            ) {
                vacancy.lookingNumber?.let {
                    val declension = if (it % 10 in listOf(2, 3, 4) && it / 10 != 1) "человека" else "человек"
                    Column {
                        Text("Сейчас просматривает $it $declension")
                        Spacer(Modifier.height(10.dp))
                    }
                }
                vacancy.title?.let {
                    Column {
                        Text(text = it)
                        Spacer(Modifier.height(10.dp))
                    }
                }
                vacancy.address?.town?.let {
                    Text(text = it)
                }
                vacancy.company?.let {
                    Column {
                        Row {
                            Text(text = it)
                            Image(painter = painterResource(R.drawable.check_mark), contentDescription = null)
                        }
                        Spacer(Modifier.height(10.dp))
                    }
                }
                vacancy.experience?.previewText?.let {
                    Column {
                        Row {
                            Image(painter = painterResource(R.drawable.experience), contentDescription = null)
                            Text(text = it)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                vacancy.publishedDate?.let {
                    val (year, month, day) = it.split("-")
                    val monthWord = when(month) {
                        "01" -> "января"
                        "02" -> "февраля"
                        "03" -> "марта"
                        "04" -> "апреля"
                        "05" -> "мая"
                        "06" -> "июня"
                        "07" -> "июля"
                        "08" -> "августа"
                        "09" -> "сентября"
                        "10" -> "октября"
                        "11" -> "ноября"
                        "12" -> "декабря"
                        else -> ""
                    }
                    Column {
                        Text(text = "Опубликовано $day $monthWord")
                        Spacer(modifier = Modifier.height(21.dp))
                    }
                }
                Button(modifier = Modifier.padding(horizontal = 16.dp), onClick = {}) {
                    Text("Откликнуться")
                }
            }

            Image(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clickable {
                        if (vacancy.isFavorite == false) {
                            addToFavorites(vacancy)
                        }
                        else {
                            removeFromFavorites(vacancy)
                        }
                        favoriteNumberViewModel.refreshFavoritesNumber()
                    },
                painter = painterResource(if (vacancy.isFavorite == true) R.drawable.favorite_active else R.drawable.favorite_inactive),
                contentDescription = null
            )
        }
    }
}