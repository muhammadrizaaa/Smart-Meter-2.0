package org.riza0004.smartmeter20.ui.component.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.riza0004.smartmeter20.R
import org.riza0004.smartmeter20.ui.component.button.CustomTextButton

@Composable
fun DialogList(
    title: String,
    subtitle: String,
    list: List<String>,
    onAction: (Int) -> Unit,
    onDismiss: () -> Unit,
    refreshable: Boolean,
    onRefresh: () -> Unit,
    emptyText: String
){
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(horizontal = 32.dp, vertical = 64.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(R.color.white)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(vertical = 8.dp).padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = colorResource(R.color.black)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = subtitle,
                        style = MaterialTheme.typography.bodyLarge,
                        color = colorResource(R.color.black)
                    )
                    if(refreshable){
                        IconButton(
                            onClick = onRefresh
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.baseline_refresh_24),
                                contentDescription = null,
                                tint = colorResource(R.color.black),
                                modifier = Modifier.size(18.dp),

                                )
                        }
                    }
                }
                if(list.isEmpty()){
                    Text(
                        emptyText,
                        style = MaterialTheme.typography.bodyLarge,
                        color = colorResource(R.color.black)
                    )
                }
                else{
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth().weight(1f),
                    ) {
                        itemsIndexed(list) { index, item ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {onAction(index)},
                                colors = CardDefaults.cardColors(
                                    containerColor = colorResource(R.color.light_main),
                                    contentColor = colorResource(R.color.black)
                                )
                            ) {
                                Text(
                                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                                    text = item,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = colorResource(R.color.black)
                                )
                            }
                        }
                    }
                }

                CustomTextButton(
                    containerColor = colorResource(R.color.white),
                    contentColor = colorResource(R.color.main),
                    text = stringResource(R.string.close),
                    border = BorderStroke(1.dp, colorResource(R.color.main))
                ) {
                    onDismiss()
                }
            }
        }
    }
}