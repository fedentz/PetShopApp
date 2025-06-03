package com.fedenintzel.petshopapp.presentation.screen.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.Preview
import com.fedenintzel.petshopapp.R
import com.fedenintzel.petshopapp.domain.model.settings.FakeSettingsDataProvider
import com.fedenintzel.petshopapp.domain.model.settings.SettingsFaqItems
import com.fedenintzel.petshopapp.presentation.components.SettingsBaseScreen
import com.fedenintzel.petshopapp.ui.theme.PetShopAppTheme
import com.fedenintzel.petshopapp.ui.theme.Poppins

/**
 * Pantalla de FAQ dentro de Settings, con ítems colapsables.
 * Muestra preguntas y respuestas dinámicamente desde Fakedata.
 */
@Composable
fun SettingsFaqScreen(
    onBackClick: () -> Unit,
    faqItems: List<SettingsFaqItems> = FakeSettingsDataProvider.getSettingsFaq()
) {
    // Estados de expansión de cada ítem
    val expandedStates = remember { mutableStateListOf<Boolean>().apply { addAll(List(faqItems.size) { false }) } }

    SettingsBaseScreen(
        title = "FAQ",
        onBackClick = onBackClick
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Column {
            faqItems.forEachIndexed { index, item ->
                val isExpanded = expandedStates[index]

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expandedStates[index] = !isExpanded }
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.question,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )

                    Image(
                        painter = painterResource(
                            id = if (isExpanded) R.drawable.arrow_up else R.drawable.arrow_down
                        ),
                        contentDescription = null
                    )
                }

                if (isExpanded) {
                    Text(
                        text = item.answer,
                        fontFamily = Poppins,
                        fontSize = 14.sp,
                        color = Color(0xFFB3B1B0),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsFaqScreenPreview() {
    PetShopAppTheme {
        SettingsFaqScreen(onBackClick = {})
    }
}
