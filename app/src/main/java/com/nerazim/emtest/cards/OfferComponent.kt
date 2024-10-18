package com.nerazim.emtest.cards

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nerazim.emtest.R
import com.nerazim.emtest.domain.models.Offer

//блок рекомендации
@Composable
fun OfferComponent(offer: Offer) {

    //контекст для тостов
    val context = LocalContext.current

    //поверхность карточки
    Surface(
        color = MaterialTheme.colorScheme.onSurface, //установка цвета
        modifier = Modifier
            .width(132.dp) //размеры карточки
            .height(120.dp)
            .clip(shape = RoundedCornerShape(8.dp)) //скругленные края
            .clickable { //весь блок кликабельный
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
        //столбец, содержащий все элементы
        Column(
            modifier = Modifier
                .padding(start = 8.dp, top = 10.dp) //отступы
        ) {
            //иконка, если она есть
            offer.id?.let {
                val size = 32.dp //размер иконки всегда одинаковый
                //иконка меняется в зависимости от рекомендации
                Image(
                    painter = painterResource(when(it) {
                        stringResource(R.string.near_vacancies) -> R.drawable.near_vacancies
                        stringResource(R.string.level_up_resume) -> R.drawable.level_up_resume
                        stringResource(R.string.temporary_job) -> R.drawable.temporary_jobs
                        else -> R.drawable.filter
                    }),
                    contentDescription = it,
                    modifier = Modifier.size(size)
                )
                //отступ
                Spacer(modifier = Modifier.height(16.dp))
            }
            //текстовое описание рекомендации
            offer.title?.let {
                Text(
                    text = it,
                    //максимум строк зависит от наличия кнопки
                    maxLines = if (offer.button == null) 3 else 2,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            //кнопка, если она есть
            offer.button?.let {
                Text(
                    text = it.text,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}