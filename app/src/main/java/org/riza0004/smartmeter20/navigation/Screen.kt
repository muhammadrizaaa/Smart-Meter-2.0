package org.riza0004.smartmeter20.navigation

import org.riza0004.smartmeter20.ui.screen.detailgroup.KEY_ID_GROUP
import org.riza0004.smartmeter20.ui.screen.detailgroup.KEY_NAME_GROUP
import org.riza0004.smartmeter20.ui.screen.usagereportmain.NAV_ID

sealed class Screen(val route: String) {
    data object HomeScreen: Screen("home_screen")
    data object ProfileScreen: Screen("profile_screen")
    data object UsageReportMainScreen: Screen("usage_report_main_screen/{$NAV_ID}"){
        fun withData(navId: Int) = "usage_report_main_screen/$navId"
    }
    data object DetailGroupScreen: Screen("detailGroupScreen/{$KEY_ID_GROUP}/{$KEY_NAME_GROUP}"){
        fun withData(id: String, name: String) = "detailGroupScreen/$id/$name"
    }
}