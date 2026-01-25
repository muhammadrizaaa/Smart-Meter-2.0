package org.riza0004.smartmeter20.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.riza0004.smartmeter20.R
import org.riza0004.smartmeter20.ui.theme.SmartMeter20Theme
import java.text.DecimalFormat

@Composable
fun UsageReport(
    modifier: Modifier = Modifier,
    onUsageReportClick: () -> Unit = {}
){
    val data = listOf(
        "Harian",
        "Bulanan",
        "Per-jam"
    )
    var selectedData by remember { mutableStateOf(data[0]) }
    var isExpanded by remember { mutableStateOf(false) }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.usage_report),
                style = MaterialTheme.typography.headlineSmall
            )
            IconButton(
                onClick = {
                    onUsageReportClick()
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_keyboard_arrow_right_24),
                    contentDescription = stringResource(R.string.show_more),
                    tint = colorResource(R.color.black),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        modifier = Modifier.padding(end = 8.dp),
                        text = stringResource(R.string.energy, formatDecimal(0.00003)),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = stringResource(R.string.power, formatDecimal(16.toDouble())),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Text(
                    modifier = Modifier.padding(end = 8.dp),
                    text = stringResource(R.string.outcome, 50000),
                    style = MaterialTheme.typography.bodyMedium
                )
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
        Box(
            modifier = Modifier.fillMaxWidth()
        ){
            CustomLineChart(
                Modifier.height(150.dp),
                steps = 4
            )
        }
    }
}

fun formatDecimal(value: Double): String {
    return DecimalFormat("0.####").format(value)
}

@Preview(showBackground = true)
@Composable
fun UsageReportPreview() {
    SmartMeter20Theme {
        UsageReport()
    }
}