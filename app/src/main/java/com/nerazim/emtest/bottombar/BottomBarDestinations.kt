package com.nerazim.emtest.bottombar

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import com.nerazim.emtest.R
import com.nerazim.emtest.screens.FavoritesScreen
import com.nerazim.emtest.screens.MessagesScreen
import com.nerazim.emtest.screens.ProfileScreen
import com.nerazim.emtest.screens.RepliesScreen
import com.nerazim.emtest.screens.SearchScreen

//список возможных назначений в навигации
sealed class BottomBarDestination(
    @DrawableRes val active: Int, //id drawable иконки выбранной
    @DrawableRes val inactive: Int, //id drawable иконки невыбранной
    val route: String, //путь
    val screen: @Composable () -> Unit = {} //сам экран в виде функции
) {
    //поиск
    data object Search: BottomBarDestination(
        active = R.drawable.search_active,
        inactive = R.drawable.search_inactive,
        route = "Поиск",
        screen = { SearchScreen() }
    )

    //избранное
    data object Favorite: BottomBarDestination(
        active = R.drawable.favorite_active,
        inactive = R.drawable.favorite_inactive,
        route = "Избранное",
        screen = { FavoritesScreen() }
    )

    //отклики
    data object Replies: BottomBarDestination(
        active = R.drawable.replies_active,
        inactive = R.drawable.replies_inactive,
        route = "Отклики",
        screen = { RepliesScreen() }
    )

    //сообщения
    data object Messages: BottomBarDestination(
        active = R.drawable.messages_active,
        inactive = R.drawable.messages_inactive,
        route = "Сообщения",
        screen = { MessagesScreen() }
    )

    //профиль
    data object Profile: BottomBarDestination(
        active = R.drawable.profile_active,
        inactive = R.drawable.profile_inactive,
        route = "Профиль",
        screen = { ProfileScreen() }
    )
}