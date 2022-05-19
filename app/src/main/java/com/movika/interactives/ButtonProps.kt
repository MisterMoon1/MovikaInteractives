package com.movika.interactives

import kotlinx.serialization.Serializable

@Serializable
data class ButtonProps(
    val background: String? = null,
    val shape: String? = null,
    val text: String? = null,
    val timer: Long? = null,
)