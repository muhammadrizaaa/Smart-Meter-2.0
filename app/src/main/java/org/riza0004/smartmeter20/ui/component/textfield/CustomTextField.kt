package org.riza0004.smartmeter20.ui.component.textfield

import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import org.riza0004.smartmeter20.R

@Composable
fun CustomTextField(
    value: String,
    onValChange: (String) -> Unit
){
    val scrollState = rememberScrollState()
//    OutlinedTextField()
    LaunchedEffect(value) {
        scrollState.animateScrollTo(scrollState.maxValue)
    }
    BasicTextField(
        value = value,
        onValueChange = {
            onValChange(it)
            },
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        decorationBox = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = stringResource(R.string.name),
                    style = MaterialTheme.typography.bodyMedium
                )
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = colorResource(R.color.black),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .wrapContentHeight(),
                    contentAlignment = Alignment.CenterStart
                ){
                    Row(
                        modifier = Modifier.horizontalScroll(scrollState).fillMaxWidth().padding(vertical = 6.dp, horizontal = 8.dp)
                    ) {
                        it()
                    }
                }
            }
        }
    )
}