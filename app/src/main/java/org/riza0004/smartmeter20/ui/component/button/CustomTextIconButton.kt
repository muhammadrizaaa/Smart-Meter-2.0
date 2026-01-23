package org.riza0004.smartmeter20.ui.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextIconButton(
    text: String,
    icon: Painter,
    modifier: Modifier = Modifier,
    containerColor: Color,
    contentColor: Color,
    border: BorderStroke? = null,
    onClick: () -> Unit,
    isTextFirst: Boolean = true
){
    TextButton(
        onClick = {onClick()},
        colors = ButtonDefaults.textButtonColors(
            containerColor, contentColor
        ),
        shape = RoundedCornerShape(8.dp),
        border = border
    ) {
        Row(
            modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if(!isTextFirst){
                Icon(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge
            )
            if(isTextFirst){
                Icon(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }

        }
    }
}