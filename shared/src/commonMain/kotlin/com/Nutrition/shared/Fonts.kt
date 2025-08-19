package com.nutrition.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import nutritionsport_kmp.shared.generated.resources.Res
import nutritionsport_kmp.shared.generated.resources.bebas_neue_regular
import nutritionsport_kmp.shared.generated.resources.roboto_condensed_medium
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.ExperimentalResourceApi

@Composable
fun BebasNeueFont() = FontFamily(
    Font(Res.font.bebas_neue_regular)
)

@Composable
fun RobotoCondensedFont() =  FontFamily(
    Font(Res.font.roboto_condensed_medium)
)

object FontSize {
     val EXTRA_SMALL = 10.sp
     val SMALL = 12.sp
     val EXTRA_REGULAR = 16.sp
     val MEDIUM = 18.sp
     val EXTRA_MEDIUM = 20.sp
     val LARGE = 30.sp
     val EXTRA_LARGE = 46.sp
     val REGULAR = 14.sp
}