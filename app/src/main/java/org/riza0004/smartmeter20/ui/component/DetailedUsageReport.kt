package org.riza0004.smartmeter20.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import org.riza0004.smartmeter20.R

@Composable
fun DetailedUsageReport(
    modifier: Modifier = Modifier,
    type: Int
){
    val data = listOf(
        "Harian",
        "Bulanan",
        "Per-jam"
    )
    var selectedData by remember { mutableStateOf(data[0]) }
    var isExpanded by remember { mutableStateOf(false) }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.fillMaxWidth().weight(1f),
                text = stringResource(R.string.usage_report),
                style = MaterialTheme.typography.headlineSmall
            )
            if(type == 2){
                Switch(
                    checked = true,
                    onCheckedChange = {
                    },
                    colors = SwitchDefaults.colors(
                        checkedIconColor = colorResource(R.color.white),
                        checkedTrackColor = colorResource(R.color.main),
                        uncheckedTrackColor = colorResource(R.color.grey),
                        uncheckedIconColor = colorResource(R.color.white),
                        uncheckedBorderColor = colorResource(R.color.grey),
                        uncheckedThumbColor = colorResource(R.color.white),
                        checkedThumbColor = colorResource(R.color.white)
                    ),
                    modifier = Modifier
                        .padding(bottom = 2.dp)
                        .width(40.dp)
                        .height(26.dp)
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(end = 8.dp),
                    text = stringResource(R.string.outcome, 500000),
                    style = MaterialTheme.typography.bodyMedium
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        modifier = Modifier.padding(end = 8.dp),
                        text = stringResource(R.string.power, formatDecimal(20.toDouble())),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = stringResource(R.string.current, formatDecimal(0.2)),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        modifier = Modifier.padding(end = 8.dp),
                        text = stringResource(R.string.energy, formatDecimal(0.002)),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = stringResource(R.string.voltage, formatDecimal(200.toDouble())),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            Dropdown(
                isExpanded = isExpanded,
                data = data,
                onClick = { isExpanded = !isExpanded },
                onCLose = {
                    isExpanded = false
                },
                onSelect = {
                    selectedData = it
                },
                selectedData = selectedData
            )
        }
        CustomLineChart(
            steps = 9
        )
    }
}