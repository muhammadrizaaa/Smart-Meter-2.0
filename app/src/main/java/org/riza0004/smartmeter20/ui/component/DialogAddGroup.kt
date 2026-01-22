package org.riza0004.smartmeter20.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.riza0004.smartmeter20.R

@Composable
fun DialogAddGroup(
    onDismiss: () -> Unit
){
    var name by remember { mutableStateOf("") }
    Dialog(
        onDismissRequest = {onDismiss()},
    ) {
        Card(
            modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(horizontal = 32.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(R.color.white)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(vertical = 8.dp).padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(R.string.add_group),
                    style = MaterialTheme.typography.titleLarge,
                    color = colorResource(R.color.black)
                )
                CustomTextField(
                    value = name
                ) {
                    name = it
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    CustomTextButton(
                        containerColor = colorResource(R.color.white),
                        contentColor = colorResource(R.color.main),
                        text = stringResource(R.string.close),
                        border = BorderStroke(1.dp, colorResource(R.color.main))
                    ) {
                        onDismiss()
                    }
                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )
                    CustomTextButton(
                        containerColor = colorResource(R.color.main),
                        contentColor = colorResource(R.color.white),
                        text = stringResource(R.string.add)
                    ) {
                        onDismiss()
                    }
                }
            }
        }
    }
}