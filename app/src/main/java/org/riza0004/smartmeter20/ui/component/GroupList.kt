package org.riza0004.smartmeter20.ui.component

import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.riza0004.smartmeter20.R
import org.riza0004.smartmeter20.ui.screen.homescreen.HomeViewModel

@Composable
fun GroupList(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    onClick: (Int) -> Unit
){
    val dataDropdown = listOf(
        "Nama A-Z",
        "Nama Z-A"
    )
    var selectedData by remember { mutableStateOf(dataDropdown[0]) }
    var isExpanded by remember { mutableStateOf(false) }
    val data = viewModel.data
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
        if(data.isEmpty()){
            Text(
                text = "Kosong",
                style = MaterialTheme.typography.titleLarge,
                color = colorResource(R.color.grey),
                textAlign = TextAlign.Center
            )
        }
        else{
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(data){index, group->
                    var items by remember { mutableIntStateOf(0) }
                    LaunchedEffect(viewModel.dataId[index]) {
                        viewModel.getSmartMeterCount(
                            groupId = viewModel.dataId[index],
                            onResult = {items = it},
                        )
                    }
                    GroupGridItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f),
                        isOn = false,
                        name = group.name,
                        items = items,
                        power = 0F,
                        energy = 0F,
                        onChecked = {},
                        onClick = {
                            onClick(index)
                        }
                    )
                }
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
    onChecked: () -> Unit,
    onClick: () -> Unit
    ){
    Card(
        modifier = modifier.clickable(
            onClick = {onClick()}
        ),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.light_main),
            contentColor = colorResource(R.color.black)
        )
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
        ) {
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 4.dp),
                        text = name,
                        style = MaterialTheme.typography.titleLarge,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "$items metering",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
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
                    modifier = Modifier
                        .padding(bottom = 2.dp)
                        .width(40.dp)
                        .height(26.dp)
                )
            }
            Row(
                modifier = Modifier.padding(top = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    maxLines = 1,
                    text = "${formatDecimal(0.2022)} Kwh",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    maxLines = 1,
                    text = "$power W",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}