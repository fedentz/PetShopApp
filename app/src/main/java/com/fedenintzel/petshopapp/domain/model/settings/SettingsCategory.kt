package com.fedenintzel.petshopapp.domain.model.settings

data class SettingsCategory(
    val title: String,
    val items: List<SettingsItem>
)