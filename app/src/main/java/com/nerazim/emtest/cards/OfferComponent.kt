package com.nerazim.emtest.cards

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nerazim.emtest.R
import com.nerazim.emtest.domain.Offer

//блок рекомендации
@Composable
fun OfferComponent(offer: com.nerazim.emtest.domain.Offer) {
    val context = LocalContext.current

    Surface(
        color = Color(0xFF222325),
        modifier = Modifier.width(132.dp).height(120.dp).clickable { //весь блок кликабельный
            try {
                //пытаемся перейти по ссылке
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(offer.link)))
            }
            //если не получается, даем знать пользователю
            catch (e: Exception) {
                Toast.makeText(context, "Что-то пошло не так!", Toast.LENGTH_SHORT).show()
            }
        }
    ) {
        Column {
            //иконка, если она есть
            offer.id?.let {
                when (offer.id) {
                    "near_vacancies" -> Image(painter = painterResource(R.drawable.near_vacancies), contentDescription = null)
                    "level_up_resume" -> Image(painter = painterResource(R.drawable.level_up_resume), contentDescription = null)
                    "temporary_job" -> Image(painter = painterResource(R.drawable.temporary_jobs), contentDescription = null)
                }
            }
            //текстовое описание рекомендации
            offer.title?.let {
                Text(
                    text = it,
                    maxLines = if (offer.button == null) 3 else 2
                )
            }
            //кнопка, если она есть
            offer.button?.let {
                Text(
                    text = it.text
                )
            }
        }
    }
}