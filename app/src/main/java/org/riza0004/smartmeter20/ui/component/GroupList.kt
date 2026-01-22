package org.riza0004.smartmeter20.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.riza0004.smartmeter20.R
import org.riza0004.smartmeter20.dataclass.testData
import org.riza0004.smartmeter20.ui.theme.SmartMeter20Theme

@Composable
fun GroupList(
    modifier: Modifier = Modifier
){
    val data = testData
    val dataDropdown = listOf(
        "Nama A-Z",
        "Nama Z-A"
    )
    var selectedData by remember { mutableStateOf(dataDropdown[0]) }
    var isExpanded by remember { mutableStateOf(false) }
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.group_list),
                style = MaterialTheme.typography.headlineSmall
            )
            Row(
                modifier = Modifier.wrapContentWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${stringResource(R.string.sort_by)}: ",
                    style = MaterialTheme.typography.bodyMedium
                )
                Dropdown(
                    selectedData = selectedData,
                    isExpanded = isExpanded,
                    data = dataDropdown,
                    onClick = {
                        isExpanded = !isExpanded
                    },
                    onCLose = {
                        isExpanded = false
                    },
                    onSelect = {
                        selectedData = it
                    }
                )
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(data){it->
                GroupGridItem(
                    modifier = Modifier.fillMaxWidth().weight(0.5f),
                    isOn = it.isOn,
                    name = it.name,
                    items = it.items.size,
                    power = it.power,
                    energy = it.energy,
                    onChecked = {
//                        val updatedGroup = it.copy(
//                            items = it.items.map {
//                                it.copy(isOn = !it.isOn)
//                            }
//                        )
                    }
                )
            }
        }
    }
}

@Composable
fun GroupGridItem(
    modifier: Modifier = Modifier,
    name: String,
    items: Int,
    power: Float,
    energy: Float,
    isOn: Boolean,
    onChecked: () -> Unit
    ){
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.light_main),
            contentColor = colorResource(R.color.black)
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$items metering",
                    style = MaterialTheme.typography.bodyMedium
                )
                Switch(
                    checked = isOn,
                    onCheckedChange = {
                        onChecked()
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
                    modifier = Modifier.width(40.dp).height(26.dp)
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$energy Kwh",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "$power W",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GroupListPreview() {
    SmartMeter20Theme {
        GroupList()
    }
}