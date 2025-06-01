package com.fedenintzel.petshopapp.domain.model.settings

import com.fedenintzel.petshopapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

object FakeSettingsDataProvider {
    fun getSettingsCategories(): List<SettingsCategory> {
        return listOf(
            SettingsCategory(
                title = "Account",
                items = listOf(
                    SettingsItem(iconResId = R.drawable.ic_profile,
                                 title = "Account",
                                 trailingContent = {
                                     Image(
                                         painter = painterResource(id = R.drawable.arrow_right),
                                         contentDescription = null,
                                         modifier = Modifier.size(24.dp)
                                     )
                                 }),
                    SettingsItem(iconResId = R.drawable.ic_home,
                                title = "Address",
                                trailingContent = {
                                    Image(
                                        painter = painterResource(id = R.drawable.arrow_right),
                                        contentDescription = null,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }),
                    SettingsItem(iconResId = R.drawable.ic_notification,
                                title = "Notification",
                                trailingContent = {
                                    Image(
                                        painter = painterResource(id = R.drawable.arrow_right),
                                        contentDescription = null,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }),
                    SettingsItem(iconResId = R.drawable.ic_wallet,
                                title = "Payment Method",
                                trailingContent = {
                                    Image(
                                        painter = painterResource(id = R.drawable.arrow_right),
                                        contentDescription = null,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }),
                    SettingsItem(iconResId = R.drawable.ic_danger_circle,
                                title = "Privacy",
                                trailingContent = {
                                    Image(
                                        painter = painterResource(id = R.drawable.arrow_right),
                                        contentDescription = null,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }),
                    SettingsItem(iconResId = R.drawable.ic_password,
                                title = "Security",
                                trailingContent = {
                                Image(
                                    painter = painterResource(id = R.drawable.arrow_right),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                            })
                        )
                    ),
            SettingsCategory(
                title = "Help",
                items = listOf(
                    SettingsItem(iconResId = R.drawable.ic_call,
                                title = "Contact Us",
                                trailingContent = {
                                    Image(
                                        painter = painterResource(id = R.drawable.arrow_right),
                                        contentDescription = null,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                    ),
                    SettingsItem(iconResId = R.drawable.ic_document,
                                title = "FAQ",
                                trailingContent = {
                                    Image(
                                        painter = painterResource(id = R.drawable.arrow_right),
                                        contentDescription = null,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                    )
                )
            )
        )
    }

    fun getSettingsNotifications(): List<SettingsCategory> {
        return listOf(
            SettingsCategory(
                title = "Social",
                items = listOf(
                    SettingsItem(
                        title = "Liked Post",
                        trailingContent = {
                            Switch(
                                checked = true,
                                onCheckedChange = { },
                                modifier = Modifier.size(width = 50.dp, height = 30.dp),
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = Color(0xFF7140FD)
                                )
                            )
                        }),
                    SettingsItem(
                        title = "New Message",
                        trailingContent = {
                            Switch(
                                checked = true,
                                onCheckedChange = { },
                                modifier = Modifier.size(width = 50.dp, height = 30.dp),
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = Color(0xFF7140FD)
                                )
                            )
                        })

                )
            ),
            SettingsCategory(
                title = "Store",
                items = listOf(
                    SettingsItem(
                        title = "Item Sold",
                        trailingContent = {
                            Switch(
                                checked = true,
                                onCheckedChange = { },
                                modifier = Modifier.size(width = 50.dp, height = 30.dp),
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = Color(0xFF7140FD)
                                )
                            )
                        }
                    )
                )
            )
        )
    }

    fun getSettingsPrivacy(): List<SettingsCategory> {
        return listOf(
            SettingsCategory(
                title = "Term of Use",
                items = listOf(
                    SettingsItem(
                        title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris interdum sapien sodales mi sagittis hendrerit. Curabitur ut lectus nec orci cursus rhoncus. Donec a ultrices risus. Mauris ut erat ut urna rhoncus facilisis a eu neque. Ut iaculis viverra laoreet. In interdum, augue non auctor pharetra, felis ante gravida ante, quis mattis quam eros non quam. Vivamus scelerisque ante nec dapibus convallis. Vestibulum quis scelerisque leo. Vestibulum quis porttitor tellus, non finibus nibh. Quisque ut tempor nulla, sed consectetur tortor. Mauris volutpat viverra arcu non laoreet. Duis eu arcu nunc. Pellentesque ultricies facilisis faucibus. Duis magna sem, ultricies sed scelerisque efficitur, hendrerit at arcu.",
                        )
                )
            ),
            SettingsCategory(
                title = "PetApp Service",
                items = listOf(
                    SettingsItem(
                        title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris interdum sapien sodales mi sagittis hendrerit. Curabitur ut lectus nec orci cursus rhoncus. Donec a ultrices risus. Mauris ut erat ut urna rhoncus facilisis a eu neque. Ut iaculis viverra laoreet. In interdum, augue non auctor pharetra, felis ante gravida ante, quis mattis quam eros non quam. Vivamus scelerisque ante nec dapibus convallis. Vestibulum quis scelerisque leo. Vestibulum quis porttitor tellus, non finibus nibh. Quisque ut tempor nulla, sed consectetur tortor. Mauris volutpat viverra arcu non laoreet. Duis eu arcu nunc. Pellentesque ultricies facilisis faucibus. Duis magna sem, ultricies sed scelerisque efficitur, hendrerit at arcu.",

                    )
                )
            )
        )
    }


    fun getSettingsSecurity(): List<SettingsCategory> {
        return listOf(
            SettingsCategory(
                title = "Security",
                items = listOf(
                    SettingsItem(iconResId = R.drawable.ic_password,
                        title = "Change Password",
                        trailingContent = {
                            Image(
                                painter = painterResource(id = R.drawable.arrow_right),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }),
                    SettingsItem(iconResId = R.drawable.ic_password,
                        title = "Change Email",
                        trailingContent = {
                            Image(
                                painter = painterResource(id = R.drawable.arrow_right),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        })
                )
            )
        )
    }

    fun getSettingsFaq() :List<SettingsFaqItems>{
        return listOf(
            SettingsFaqItems(question = "Security", answer = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris interdum sapien sodales mi sagittis hendrerit."),
            SettingsFaqItems(question = "Account", answer = "Podés modificar tu cuenta desde el menú de perfil, sección Account."),
            SettingsFaqItems(question = "Payment", answer = "Aceptamos tarjetas de crédito y débito Visa, Mastercard y American Express."),
            SettingsFaqItems(question = "Shipping", answer = "Los envíos demoran entre 3 y 5 días hábiles."),
            SettingsFaqItems(question = "Returns", answer = "Tenés hasta 30 días para realizar devoluciones sin costo.")

            )
    }

}

