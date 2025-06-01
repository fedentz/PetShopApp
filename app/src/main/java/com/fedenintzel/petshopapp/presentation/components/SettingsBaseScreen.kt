package com.fedenintzel.petshopapp.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedenintzel.petshopapp.R
import com.fedenintzel.petshopapp.ui.theme.Poppins
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing


/**
 * Pantalla base reutilizable para la sección de Settings.
 *
 * Incluye:
 * - TopBar con botón atrás y título.
 * - Contenido central scrollable o estático.
 * - Botón inferior opcional (lleno o outlined).
 *
 * @param title Título que aparece en la TopBar
 * @param onBackClick Acción que se ejecuta al tocar el botón atrás
 * @param modifier Modifier externo para customización
 * @param showBottomButton Si el botón inferior debe mostrarse o no
 * @param bottomButtonText Texto del botón inferior (ej: "Save Changes")
 * @param onBottomButtonClick Acción del botón inferior
 * @param bottomButtonFilled Si el botón inferior es filled (true) u outlined (false)
 * @param content Contenido que se muestra en el cuerpo de la pantalla
 */
@Composable
fun SettingsBaseScreen(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    showBottomButton: Boolean = false,
    bottomButtonText: String = "",
    onBottomButtonClick: () -> Unit = {},
    bottomButtonFilled: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {
    Scaffold(

        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        WindowInsets.safeDrawing.only(WindowInsetsSides.Top).asPaddingValues()
                    ) // padding superior según notch
                    .padding(horizontal = 16.dp, vertical = 12.dp), // padding visual adicional
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .size(46.dp)
                        .shadow(
                            elevation = 10.dp,
                            shape = RoundedCornerShape(16.dp),
                            clip = false,
                            ambientColor = Color(0x33000000),
                            spotColor = Color(0x33000000)
                        )
                        .background(Color.White, shape = RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .align(Alignment.CenterStart)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_left),
                        contentDescription = "Back"
                    )
                }

                Text(
                    text = title,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        },
        bottomBar = {
            if (showBottomButton) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = onBottomButtonClick,
                        shape = RoundedCornerShape(100),
                        colors = if (bottomButtonFilled)
                            ButtonDefaults.buttonColors(containerColor = Color(0xFF7140FD))
                        else
                            ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF7140FD)),
                        border = if (!bottomButtonFilled)
                            BorderStroke(1.dp, Color(0xFF7140FD))
                        else null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                    ) {
                        Text(
                            text = bottomButtonText,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = if (bottomButtonFilled) Color.White else Color(0xFF7140FD)
                        )
                    }
                }
            }
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            content = content
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsBaseScreenPreview() {
    SettingsBaseScreen(
        title = "Settings Page",
        onBackClick = {},
        showBottomButton = true,
        bottomButtonText = "Log Out",
        onBottomButtonClick = {},
        bottomButtonFilled = false
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Acá iría el contenido...",
            fontFamily = Poppins,
            fontSize = 14.sp,
            color = Color.Black
        )
    }
}
