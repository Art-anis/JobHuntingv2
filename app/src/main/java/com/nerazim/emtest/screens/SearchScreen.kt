package com.nerazim.emtest.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nerazim.emtest.R
import com.nerazim.emtest.cards.OfferComponent
import com.nerazim.emtest.cards.VacancyComponent
import com.nerazim.emtest.viewmodels.SearchViewModel
import com.nerazim.emtest.viewmodels.ViewModelFactory

@Composable
fun SearchScreen() {
    val viewModel: SearchViewModel = viewModel(factory = ViewModelFactory.Factory)

    val offers by viewModel.getOfferListLive().observeAsState(listOf())
    val vacancies by viewModel.getVacancyListLive().observeAsState(listOf())

    var advancedSearch by remember { mutableStateOf(false) }

    Surface(Modifier.fillMaxSize()) {
        Column {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                var search by remember { mutableStateOf("") }
                TextField(
                    modifier = Modifier.weight(0.85f, true),
                    value = search,
                    onValueChange = { value -> search = value },
                    placeholder = {
                        Text(
                            text = stringResource(if (advancedSearch) R.string.advanced_search_placeholder else R.string.search_placeholder),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    leadingIcon = {
                        Image(
                            painter = painterResource(if (advancedSearch) R.drawable.back_arrow else R.drawable.search_inactive),
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                if (advancedSearch) {
                                    advancedSearch = false
                                }
                            }
                        )
                    }
                )

                IconButton(
                    modifier = Modifier,
                    colors = IconButtonDefaults.iconButtonColors(containerColor = Color(0xFF313234),
                        contentColor = Color.White),
                    onClick = {}
                ) {
                    Image(painter = painterResource(R.drawable.filter), contentDescription = null)
                }
            }
            Spacer(Modifier.height(16.dp))

            if (!advancedSearch) {
                LazyRow(
                    Modifier.padding(start = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(offers) { offer ->
                        OfferComponent(offer)
                    }
                }

                Spacer(Modifier.height(32.dp))
            }
            else {
                Row(modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("${vacancies.size} ${getVacancyDeclension(vacancies.size)}")
                    Row {
                        Text("По соответствию")
                        Image(
                            painter = painterResource(R.drawable.sort_by),
                            contentDescription = "Сортировка по соответсивию"
                        )
                    }
                }
                Spacer(Modifier.height(16.dp))
            }

            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f, false),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(if (advancedSearch) vacancies else vacancies.take(2)) { vacancy ->
                    VacancyComponent(
                        vacancy = vacancy,
                        addToFavorites = viewModel::addToFavorites,
                        removeFromFavorites = viewModel::removeFromFavorites
                    )
                }
            }
            if (!advancedSearch) {
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .padding(bottom = 8.dp)
                        .fillMaxWidth(),
                    onClick = { advancedSearch = true}
                ) {
                    val listRemainderSize = vacancies.size - 2
                    val declension = getVacancyDeclension(listRemainderSize)
                    Text("Еще $listRemainderSize $declension")
                }
            }
        }
    }
}

fun getVacancyDeclension(vacancies: Int) = if (vacancies % 10 in listOf(2, 3, 4) && vacancies / 10 != 1) "вакансии"
    else if (vacancies % 10 == 1 && vacancies / 10 != 1) "вакансия"
    else "вакансий"