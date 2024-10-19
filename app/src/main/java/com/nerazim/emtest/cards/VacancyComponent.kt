package com.nerazim.emtest.cards

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nerazim.emtest.R
import com.nerazim.emtest.VacancyActivity
import com.nerazim.emtest.domain.models.Vacancy
import com.nerazim.emtest.viewmodels.FavoriteNumberViewModel
import org.koin.androidx.compose.koinViewModel

//карточка вакансии
@Composable
fun VacancyComponent(
    vacancy: Vacancy, //вакансия
    addToFavorites: (Vacancy) -> Unit = {}, //функция добавления в избранное
    removeFromFavorites: (Vacancy) -> Unit //функция удаления из избранного
) {

    //viewmodel
    val favoriteNumberViewModel = koinViewModel<FavoriteNumberViewModel>()

    val context = LocalContext.current
    //поверхность
    Surface(
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier
            .fillMaxWidth() //заполняем всю ширину
            .clip(shape = RoundedCornerShape(8.dp)) //скругленные края
            .clickable {
                val i = Intent(context, VacancyActivity::class.java)
                context.startActivity(i)
            } //делаем кликабельной, переход на заглушку
    ) {
        //столбец, в котором находятся все элементы
        Column(
            //отступы со всех сторон
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            //ряд
            Row(
                //раскидываем оба элемента ряда по углам
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                //столбец с текстом
                Column(
                    //выделяем ему 2/3 карточки
                    modifier = Modifier.weight(0.67f, true)
                ) {
                    //число просматривающих вакансию
                    vacancy.lookingNumber?.let {
                        //склонение
                        val declension = if (it % 10 in listOf(2, 3, 4) && it / 10 != 1) "человека" else "человек"
                        Column {
                            Text(
                                text = "Сейчас просматривает $it $declension",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(Modifier.height(10.dp))
                        }
                    }
                    //название вакансии
                    vacancy.title?.let {
                        Column {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.titleSmall
                            )
                            Spacer(Modifier.height(10.dp))
                        }
                    }
                    //город, где находится компания
                    vacancy.address?.town?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    //название компании
                    vacancy.company?.let {
                        Column {
                            //ряд для текста и галочки
                            Row {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Image(
                                    painter = painterResource(R.drawable.check_mark),
                                    contentDescription = null
                                )
                            }
                            Spacer(Modifier.height(10.dp))
                        }
                    }
                    //опыт работы
                    vacancy.experience?.previewText?.let {
                        Column {
                            //ряд для картинки и текста
                            Row {
                                Image(
                                    painter = painterResource(R.drawable.experience),
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                    //дата публикации
                    vacancy.publishedDate?.let {
                        //разбиение даты
                        val (year, month, day) = it.split("-")
                        //склонение месяца
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
                        //сам текст
                        Text(
                            text = "Опубликовано ${day.toInt()} $monthWord",
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                }

                //иконка добавления в избранное
                Image(
                    //кликабельная, изменяет статус вакансии
                    modifier = Modifier.clickable {
                            if (vacancy.isFavorite == false) {
                                addToFavorites(vacancy)
                            }
                            else {
                                removeFromFavorites(vacancy)
                            }
                            //обновляем
                            favoriteNumberViewModel.refreshFavoritesNumber()
                        },
                    painter = painterResource(if (vacancy.isFavorite == true) R.drawable.favorite_active else R.drawable.favorite_inactive),
                    contentDescription = stringResource(R.string.favorite)
                )
            }
            Spacer(modifier = Modifier.height(21.dp))
            //кнопка "Откликнуться"
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(50.dp)),
                onClick = {}
            ) {
                Text(
                    text = stringResource(R.string.reply),
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}