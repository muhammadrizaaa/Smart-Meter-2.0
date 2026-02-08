package org.riza0004.smartmeter20.ui.screen.usagereportmain

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseUser
import org.riza0004.smartmeter20.R
import org.riza0004.smartmeter20.navigation.Screen
import org.riza0004.smartmeter20.ui.component.DetailedUsageReport
import org.riza0004.smartmeter20.util.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.Locale

const val NAV_ID = "navId"
const val METER_ID = "meterId"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsageReportMainScreen(
    navId: Int,
    navHostController: NavHostController,
    groupId: String,
    meterId: String?,
    userFlow: FirebaseUser?
){
    if(userFlow == null){
        navHostController.navigate(Screen.HomeScreen.route)
    }
    userFlow?.let {
        val context = LocalContext.current
        val factory = ViewModelFactory(userFlow, context)
        val viewModel: UsageReportViewModel = viewModel(
            factory = factory
        )
        val data = viewModel.data
        val liveData by viewModel.liveData.collectAsState()
        LaunchedEffect(data, meterId) {
            if(navId == 2 && meterId != null){
                viewModel.init(groupId, meterId)
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
                                text = when(navId) {
                                    0 -> it.displayName.toString()
                                    1 -> "group"
                                    else -> "smart meter"
                                },
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                style = MaterialTheme.typography.headlineMedium,
                                color = colorResource(R.color.light_main)
                            )
                        }
                    }
                )
            }
        ) { innerPadding->
            viewModel.observeMeter("1")
            DetailedUsageReport(
                type = navId,
                modifier = Modifier.padding(innerPadding)
                    .padding(top = 24.dp)
                    .padding(horizontal = 16.dp),
                onSwitch = {
                    if(meterId!=null){
                        viewModel.setRelay(meterId, if(liveData?.ison == 1L) 0 else 1)
                    }
                },
                liveData = liveData,
                listHistory = data,
            )
        }
    }
}

fun Timestamp.toFormattedString(): String{
    val date = toDate()
    val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    return formatter.format(date)
}