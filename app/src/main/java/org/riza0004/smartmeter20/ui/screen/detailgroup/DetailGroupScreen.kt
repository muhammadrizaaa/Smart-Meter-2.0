package org.riza0004.smartmeter20.ui.screen.detailgroup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseUser
import org.riza0004.smartmeter20.R
import org.riza0004.smartmeter20.navigation.Screen
import org.riza0004.smartmeter20.ui.component.SmartMeterList
import org.riza0004.smartmeter20.ui.component.UsageReport
import org.riza0004.smartmeter20.ui.component.button.CustomFloatingActionButton
import org.riza0004.smartmeter20.ui.component.dialog.DialogTextField
import org.riza0004.smartmeter20.util.ViewModelFactory

const val KEY_ID_GROUP = "groupId"
const val KEY_NAME_GROUP = "groupName"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailGroupScreen(
    navHostController: NavHostController,
    groupId: String,
    groupName: String,
    userFlow: FirebaseUser?
){
    if(userFlow == null){
        navHostController.navigate(Screen.HomeScreen.route)
    }
    userFlow?.let {
        val factory = ViewModelFactory(userFlow)
        val viewModel: DetailGroupViewModel = viewModel(
            factory = factory
        )
        var dialogAddSmartMeterIsOpen by remember { mutableStateOf(false) }
        var dialogEditGroupIsOpen by remember { mutableStateOf(false) }
        val data = viewModel.data
        var editedGroupName by remember { mutableStateOf(groupName) }
        LaunchedEffect(data) {
            viewModel.init(groupId)
        }
        Scaffold(
            containerColor = colorResource(R.color.white),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = colorResource(R.color.main_background)
                    ),
                    title = {
                        Row(
                            modifier = Modifier.padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = {
                                    navHostController.navigateUp()
                                }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.outline_keyboard_arrow_left_24),
                                    contentDescription = stringResource(R.string.app_name),
                                    tint = colorResource(R.color.light_main),
                                    modifier = Modifier.size(36.dp)
                                )
                            }
                            Text(
                                text = editedGroupName,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                style = MaterialTheme.typography.headlineMedium,
                                color = colorResource(R.color.light_main)
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                dialogEditGroupIsOpen = true
                            }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.baseline_edit_24),
                                contentDescription = stringResource(R.string.edit),
                                modifier = Modifier.size(36.dp),
                                tint = colorResource(R.color.light_main)
                            )
                        }
                    }
                )
            },
            floatingActionButton = {
                CustomFloatingActionButton {
                    dialogAddSmartMeterIsOpen = true
                }
            }
        ) { innerPadding->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ){
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .padding(top = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    UsageReport(
                        onUsageReportClick = { navHostController.navigate(Screen.UsageReportMainScreen.route) }
                    )
                    SmartMeterList(
                        data = viewModel.data,
                        name = groupName
                    )
                }
            }
            if(dialogEditGroupIsOpen){
                DialogTextField(
                    value = groupName,
                    onDismiss = { dialogEditGroupIsOpen = false },
                    onConfirm = {
                        viewModel.updateGroup(
                            groupId = groupId,
                            newName = it
                        )
                        editedGroupName = it
                    },
                    label = stringResource(R.string.name),
                    title = stringResource(R.string.add_smart_meter),
                    textConfirmBtn = stringResource(R.string.edit)
                )
            }
            if(dialogAddSmartMeterIsOpen){
                DialogTextField(
                    onDismiss = { dialogAddSmartMeterIsOpen = false },
                    onConfirm = {
                        viewModel.insertSmartMeter(
                            groupId = groupId,
                            name = it
                        )
                    },
                    label = stringResource(R.string.name),
                    title = stringResource(R.string.add_smart_meter),
                    textConfirmBtn = stringResource(R.string.add)
                )
            }
        }
    }
}