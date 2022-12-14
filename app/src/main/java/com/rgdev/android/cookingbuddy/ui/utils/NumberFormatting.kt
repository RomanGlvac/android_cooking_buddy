package com.rgdev.android.cookingbuddy.ui.utils

fun createPriceString(price: Double, formatString: String): String {
    return formatString.format(price)
}