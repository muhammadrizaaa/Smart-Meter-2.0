package org.riza0004.smartmeter20.navigation

import org.riza0004.smartmeter20.ui.screen.detailgroup.KEY_ID_GROUP
import org.riza0004.smartmeter20.ui.screen.detailgroup.KEY_NAME_GROUP
import org.riza0004.smartmeter20.ui.screen.usagereportmain.METER_ID
import org.riza0004.smartmeter20.ui.screen.usagereportmain.NAV_ID

sealed class Screen(val route: String) {
    data object HomeScreen: Screen("home_screen")
    data object ProfileScreen: Screen("profile_screen")
    data object UsageReportMainScreen: Screen("usage_report_main_screen/{$NAV_ID}/{$KEY_ID_GROUP}/{$METER_ID}"){
        fun withData(navId: Int, meterId: String?, groupId: String) = "usage_report_main_screen/$navId/$groupId/$meterId"
    }
    data object DetailGroupScreen: Screen("detailGroupScreen/{$KEY_ID_GROUP}/{$KEY_NAME_GROUP}"){
        fun withData(id: String, name: String) = "detailGroupScreen/$id/$name"
    }
}