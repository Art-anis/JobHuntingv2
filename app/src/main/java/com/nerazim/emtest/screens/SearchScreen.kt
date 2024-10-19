package com.nerazim.emtest.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nerazim.emtest.R
import com.nerazim.emtest.cards.OfferComponent
import com.nerazim.emtest.cards.VacancyComponent
import com.nerazim.emtest.viewmodels.SearchViewModel
import org.koin.androidx.compose.koinViewModel

//экран поиска
@Composable
fun SearchScreen() {
    //viewmodel
    val viewModel = koinViewModel<SearchViewModel>()

    //рекомендации и вакансии
    val offers by viewModel.getOfferListLive().observeAsState(listOf())
    val vacancies by viewModel.getVacancyListLive().observeAsState(listOf())

    //флаг поиска по соответствию
    var advancedSearch by remember { mutableStateOf(false) }

    //поверхность
    Surface(Modifier.fillMaxSize()) {
        //столбец, в котором находятся все элементы
        Column {
            Spacer(modifier = Modifier.height(16.dp))
            //ряд с поиском и кнопкой фильтра
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(56.dp),
                //установка расстояния между элементами ряда
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                //переменная состояния для запоминания значения поиска (пока не применяется)
                var search by remember { mutableStateOf("") }
                //поле поиска
                TextField(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .weight(0.85f, true),
                    //устанавливаем цвета
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary,

                    ),
                    value = search,
                    onValueChange = { value -> search = value },
                    //текст меняется, если поиск по соответствию
                    placeholder = {
                        Text(
                            text = stringResource(if (advancedSearch) R.string.advanced_search_placeholder else R.string.search_placeholder),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    },
                    //иконка меняется, если поиск по соответствию
                    leadingIcon = {
                        Image(
                            painter = painterResource(if (advancedSearch) R.drawable.back_arrow else R.drawable.search_inactive),
                            contentDescription = null,
                            //нажатие делает что-то только, если поиск по соответствию
                            modifier = Modifier.clickable {
                                if (advancedSearch) {
                                    //возврат в обычный режим поиска
                                    advancedSearch = false
                                }
                            }
                        )
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                //кнопка для фильтров
                Button(
                    modifier = Modifier
                        .size(56.dp),
                    contentPadding = PaddingValues(0.dp), //чтобы отображалась иконка
                    //установка цветов
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    //форма кнопки
                    shape = RoundedCornerShape(10.dp),
                    onClick = {}
                ) {
                    //иконка
                    Image(
                        modifier = Modifier.fillMaxSize(0.6f),
                        painter = painterResource(R.drawable.filter),
                        contentDescription = stringResource(R.string.filter_description)
                    )
                }
            }
            Spacer(Modifier.height(16.dp))

            //в обычном режиме поиска
            if (!advancedSearch) {
                //ряд с рекомендациями
                LazyRow(
                    Modifier.padding(start = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    //загружаем все рекомендации
                    items(offers) { offer ->
                        OfferComponent(offer)
                    }
                }
                Spacer(Modifier.height(32.dp))
                //заголовок
                Text(
                    text = stringResource(R.string.search_headline),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            //режим поиска по соответствию
            else {
                //ряд текста
                Row(modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                    //раскидываем элементы по углам
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    //количество вакансий
                    Text(
                        text = "${vacancies.size} ${getVacancyDeclension(vacancies.size)}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    //фильтр
                    Row {
                        //текст с названием фильтра
                        Text(
                            text = stringResource(R.string.advanced_search_filter),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onTertiary
                        )
                        //иконка
                        Image(
                            painter = painterResource(R.drawable.sort_by),
                            contentDescription = stringResource(R.string.advanced_search_description)
                        )
                    }
                }
                Spacer(Modifier.height(16.dp))
            }

            //столбец с вакансиями
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f, true),
                //фиксированное расстояние между элементами
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                //в обычном поиске отображаем всего 2 вакансии, иначе все
                items(if (advancedSearch) vacancies else vacancies.take(2)) { vacancy ->
                    //карточка вакансии
                    VacancyComponent(
                        vacancy = vacancy,
                        addToFavorites = viewModel::addToFavorites,
                        removeFromFavorites = viewModel::removeFromFavorites
                    )
                }
            }
            //кнопка перехода в режим по соответствию
            if (!advancedSearch) {
                Spacer(modifier = Modifier.height(24.dp))
                //сама кнопка
                Button(
                    modifier = Modifier
                        //отступы
                        .padding(horizontal = 15.dp)
                        .padding(bottom = 8.dp)
                        //заполяняем весь доступный экран
                        .fillMaxWidth(),
                    //переход в режим поиска по соответствию
                    onClick = { advancedSearch = true},
                    //установка цветов
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onTertiary
                    ),
                    //форма кнопки
                    shape = RoundedCornerShape(8.dp)
                ) {
                    //расчет склонения
                    val listRemainderSize = vacancies.size - 2
                    val declension = getVacancyDeclension(listRemainderSize)
                    //текст
                    Text(
                        text = "Еще $listRemainderSize $declension",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
        }
    }
}

//функция получения склонения слова "вакансии"
fun getVacancyDeclension(vacancies: Int) = if (vacancies % 10 in listOf(2, 3, 4) && vacancies / 10 != 1) "вакансии"
    else if (vacancies % 10 == 1 && vacancies / 10 != 1) "вакансия"
    else "вакансий"