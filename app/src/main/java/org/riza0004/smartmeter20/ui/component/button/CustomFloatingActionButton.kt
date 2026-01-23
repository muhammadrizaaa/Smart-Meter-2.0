package org.riza0004.smartmeter20.ui.component.button

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import org.riza0004.smartmeter20.R

@Composable
fun CustomFloatingActionButton(
    onCLick: () -> Unit
){
    FloatingActionButton(
        onClick = { onCLick() },
        shape = CircleShape,
        containerColor = colorResource(R.color.main),
        contentColor = colorResource(R.color.white)
    ) {
        Icon(
            painter = painterResource(R.drawable.baseline_add_24),
            contentDescription = stringResource(R.string.add),
            tint = colorResource(R.color.white)
        )
    }
}