package com.nerazim.emtest.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

//экран откликов - заглушка
@Composable
fun RepliesScreen() {
    Surface(Modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.Center) {
            Text("Просмотр откликов пока недоступен!")
        }
    }
}
