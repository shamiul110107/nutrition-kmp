package com.nutrition.shared.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nutrition.shared.Alpha
import com.nutrition.shared.ButtonDisabled
import com.nutrition.shared.ButtonPrimary
import com.nutrition.shared.ButtonSecondary
import com.nutrition.shared.FontSize
import com.nutrition.shared.IconPrimary
import com.nutrition.shared.Resources
import com.nutrition.shared.TextPrimary
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    icon: DrawableResource? = null,
    enabled: Boolean = true,
    secondary: Boolean = false,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(size = 6.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (secondary) ButtonSecondary else ButtonPrimary,
            contentColor = TextPrimary,
            disabledContainerColor = ButtonDisabled,
            disabledContentColor = TextPrimary.copy(alpha = Alpha.DISABLED)
        ),
        contentPadding = PaddingValues(all = 20.dp)
    ) {
        if (icon != null) {
            Icon(
                modifier = Modifier.size(14.dp),
                painter = painterResource(icon),
                contentDescription = "Button icon",
                tint = if (icon == Resources.Image.PaypalLogo) Color.Unspecified
                else IconPrimary
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            fontSize = FontSize.REGULAR,
            fontWeight = FontWeight.Medium
        )
    }
}