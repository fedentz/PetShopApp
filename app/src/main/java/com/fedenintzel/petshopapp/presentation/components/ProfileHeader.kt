package com.fedenintzel.petshopapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedenintzel.petshopapp.R
import com.fedenintzel.petshopapp.ui.theme.PetShopAppTheme
import com.fedenintzel.petshopapp.ui.theme.Poppins

/**
 * Componente que muestra el encabezado del perfil con imagen de fondo,
 * avatar circular centrado y nombre del usuario.
 *
 * Este header puede ser utilizado en pantallas como "Account" o "Profile",
 * y opcionalmente permite mostrar íconos de edición sobre el fondo y el avatar.
 *
 * Parámetros:
 * @param backgroundPainter Imagen de fondo de perfil (Painter desde resources)
 * @param profileImage Imagen del avatar del usuario (Painter desde resources)
 * @param userName Texto con el nombre del usuario
 * @param isEditable Si es verdadero, muestra íconos de edición sobre el fondo y avatar (opcional, false por defecto)
 * @param onEditBackgroundClick Callback a ejecutar al tocar el ícono de editar fondo (opcional)
 * @param onEditAvatarClick Callback a ejecutar al tocar el ícono de editar avatar (opcional)
 *
 * Ejemplo de uso:
 * ```
 * ProfileHeader(
 *     backgroundPainter = painterResource(id = R.drawable.profile_background),
 *     profileImage = painterResource(id = R.drawable.profile_avatar),
 *     userName = "Abduldul",
 *     isEditable = true,
 *     onEditBackgroundClick = { /* cambiar fondo */ },
 *     onEditAvatarClick = { /* cambiar avatar */ }
 * )
 * ```
 */
@Composable
fun ProfileHeader(
    backgroundPainter: Painter,
    profileImage: Painter,
    userName: String,
    isEditable: Boolean = false,
    onEditBackgroundClick: (() -> Unit)? = null,
    onEditAvatarClick: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(327.dp)
                .height(159.dp + 50.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            // Fondo
            Image(
                painter = backgroundPainter,
                contentDescription = "Profile Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(327.dp)
                    .height(159.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .align(Alignment.TopCenter)
            )

            // Botón editar fondo
            if (isEditable && onEditBackgroundClick != null) {
                IconButton(
                    onClick = onEditBackgroundClick,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.TopEnd)
                        .offset(x = (-12).dp, y = 12.dp)
                        .background(Color.White, CircleShape)
                        .padding(4.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_edit),
                        contentDescription = "Edit Background",
                        tint = Color.Unspecified
                    )
                }
            }

            // Imagen de perfil centrada abajo
            Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                Image(
                    painter = profileImage,
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                        .background(Color(0xFFEDEDED))
                )

                // Botón editar avatar
                if (isEditable && onEditAvatarClick != null) {
                    IconButton(
                        onClick = onEditAvatarClick,
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.TopEnd)
                            .offset(x = 4.dp, y = 4.dp)
                            .background(Color.White, CircleShape)
                            .padding(4.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_edit),
                            contentDescription = "Edit Avatar",
                            tint = Color.Unspecified
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = userName,
            fontSize = 20.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileHeaderPreview() {
    PetShopAppTheme {
        ProfileHeader(
            backgroundPainter = painterResource(id = R.drawable.profile_background),
            profileImage = painterResource(id = R.drawable.profile_avatar),
            userName = "Abduldul",
            isEditable = true,
            onEditAvatarClick = {},
            onEditBackgroundClick = {},
        )
    }
}
