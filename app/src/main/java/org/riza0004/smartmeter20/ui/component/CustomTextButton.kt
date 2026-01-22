package org.riza0004.smartmeter20.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextButton(
    containerColor: Color,
    contentColor: Color,
    border: BorderStroke? = null,
    text: String,
    onClick: () -> Unit,
){
    TextButton(
        onClick = {onClick()},
        colors = ButtonDefaults.textButtonColors(
            containerColor, contentColor
        ),
        shape = RoundedCornerShape(8.dp),
        border = border
    ) {
        Text(
            modifier = Modifier.padding(vertical = 4.dp).padding(horizontal = 8.dp),
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}