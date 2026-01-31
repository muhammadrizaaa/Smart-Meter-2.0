package org.riza0004.smartmeter20.ui.screen.detailgroup

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.firebase.auth.FirebaseUser
import org.riza0004.smartmeter20.R
import org.riza0004.smartmeter20.navigation.Screen
import org.riza0004.smartmeter20.ui.component.SmartMeterList
import org.riza0004.smartmeter20.ui.component.UsageReport
import org.riza0004.smartmeter20.ui.component.button.CustomFloatingActionButton
import org.riza0004.smartmeter20.ui.component.dialog.DialogList
import org.riza0004.smartmeter20.ui.component.dialog.DialogTextField
import org.riza0004.smartmeter20.util.ViewModelFactory

const val KEY_ID_GROUP = "groupId"
const val KEY_NAME_GROUP = "groupName"

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun DetailGroupScreen(
    navHostController: NavHostController,
    groupId: String,
    groupName: String,
    userFlow: FirebaseUser?
){
    val test = true
    val context = LocalContext.current
    if(userFlow == null){
        navHostController.navigate(Screen.HomeScreen.route)
    }
    userFlow?.let {
        val permissions = rememberMultiplePermissionsState(
            permissions = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
                listOf(
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_CONNECT
                )
            } else{
                listOf(
                    Manifest.permission.BLUETOOTH,
                    Manifest.permission.BLUETOOTH_ADMIN
                )
            }
        )
        val hasBtPermission =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.BLUETOOTH_CONNECT
                ) == PackageManager.PERMISSION_GRANTED
            } else {
                true
            }
        val factory = ViewModelFactory(userFlow, context)
        val viewModel: DetailGroupViewModel = viewModel(
            factory = factory
        )
        var dialogAddSmartMeterIsOpen by remember { mutableStateOf(false) }
        var dialogEditGroupIsOpen by remember { mutableStateOf(false) }
        var dialogChooseBluetoothIsOpen by remember { mutableStateOf(false) }
        val data = viewModel.data
        var editedGroupName by remember { mutableStateOf(groupName) }
        val bluetoothList by viewModel.pairedDevices.collectAsState()
        val bluetoothNames = remember(bluetoothList) {
            bluetoothList.mapNotNull { it.name }
        }
        LaunchedEffect(Unit) {
            permissions.launchMultiplePermissionRequest()
        }
        LaunchedEffect(data) {
            viewModel.init(groupId)
        }
        LaunchedEffect(hasBtPermission) {
            if (permissions.allPermissionsGranted && hasBtPermission){
                viewModel.loadPairedDevices()
            }
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
                    if(permissions.allPermissionsGranted && hasBtPermission){
                        dialogAddSmartMeterIsOpen = true
                    }
                    else{
                        if(permissions.shouldShowRationale){
                            permissions.launchMultiplePermissionRequest()
                        }
                        else{
                            val intent = Intent(
                                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.fromParts(
                                    "package",
                                    context.packageName,
                                    null
                                )
                            )
                            context.startActivity(intent)
                        }
                    }
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
                        onUsageReportClick = {
                            val route = Screen.UsageReportMainScreen.withData(1)
                            navHostController.navigate(route)
                        }
                    )
                    SmartMeterList(
                        viewModel = viewModel,
                        name = groupName,
                        onCLick = {
                            val route = Screen.UsageReportMainScreen.withData(2)
                            navHostController.navigate(route)
                        }
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
                        if(test){
                            dialogChooseBluetoothIsOpen = true
                        }
                        else{
                            viewModel.insertSmartMeter(
                                groupId = groupId,
                                name = it
                            )
                        }
                    },
                    label = stringResource(R.string.name),
                    title = stringResource(R.string.add_smart_meter),
                    textConfirmBtn = stringResource(R.string.add)
                )
            }
            if(dialogChooseBluetoothIsOpen){
                DialogList(
                    title = stringResource(R.string.add_smart_meter),
                    subtitle = stringResource(R.string.conn_bluetooth),
                    list = bluetoothNames,
                    onAction = {  },
                    onDismiss = {
                        dialogChooseBluetoothIsOpen = false
                    },
                    refreshable = true,
                    onRefresh = {
                        viewModel.loadPairedDevices()
                    },
                    emptyText = stringResource(R.string.empty_bt_list)
                )
            }
        }
    }
}