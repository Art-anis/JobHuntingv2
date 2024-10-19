package com.nerazim.emtest.bottombar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Tab
import com.nerazim.emtest.R
import com.nerazim.emtest.viewmodels.FavoriteNumberViewModel
import org.koin.androidx.compose.koinViewModel

//нижний бар, он же навигация
@Composable
fun JobBottomBar(
    screens: List<BottomBarDestination>, //список экранов
    onTabSelected: (BottomBarDestination) -> Unit, //что делать при выборе вкладки
    currentScreen: BottomBarDestination //текущий экран
) {
    Surface(
        Modifier.fillMaxWidth()
    ) {
        //ряд, на котором размещаются все иконки
        Row(
            Modifier.selectableGroup(), //это группа предметов, из которых можно выбрать один
            horizontalArrangement = Arrangement.SpaceEvenly //равномерное распределение по всему ряду
        ) {
            //заполнение бара элементами
            screens.forEach { screen ->
                JobBottomBarItem(
                    text = screen.route, //подпись под иконкой
                    //иконка, разная в зависимости от того, выбран этот экран или нет
                    icon = painterResource (if (currentScreen == screen) screen.active else screen.inactive),
                    //что делать при выборе
                    onSelected = { onTabSelected(screen) },
                    //проверка на текущий экран
                    selected = currentScreen == screen
                )
            }
        }
    }
}

//элемент бара
@Composable
fun JobBottomBarItem(
    text: String, //подпись
    icon: Painter, //иконка
    onSelected: () -> Unit, //что делать при выборе
    selected: Boolean //флаг выбора
) {
    //столбец, в котором лежат все элементы
    Column(
        modifier = Modifier.selectable( //можно выбрать
            selected = selected,
            onClick = onSelected,
            role = Tab),
        horizontalAlignment = Alignment.CenterHorizontally //выравнивание по центру
    ) {
        //если это вкладка "Избранное, надо добавить Badge
        if (text == stringResource(R.string.favorite)) {
            //получаем viewmodel
            val viewModel = koinViewModel<FavoriteNumberViewModel>()
            //получаем число вакансий в избранном
            val favoriteNumber by viewModel.getFavoritesNumber().observeAsState()

            BadgedBox(badge = {
                //если вакансии в избранном есть, отобразить Badge
                favoriteNumber?.let {
                    if (it > 0) {
                        Badge(
                            containerColor = MaterialTheme.colorScheme.onSecondary,
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                        ) {
                            Text("$it")
                        }
                    }
                }
                //содержимое, сама иконка
            }) {
                Image(
                    painter = icon,
                    contentDescription = text
                )
            }
        }
        //все остальные вкладки
        else {
            Image(
                painter = icon,
                contentDescription = text
            ) //сама иконка (не Icon, потому что иначе они черно-белые)
        }
        //подпись, меняет цвет в зависимости от того, выбран ли этот экран
        Text(
            text = text,
            color = if (selected) MaterialTheme.colorScheme.onTertiary else MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}
