package com.example.coffeeapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection

@Composable
fun isRtl() = LocalLayoutDirection.current == LayoutDirection.Rtl