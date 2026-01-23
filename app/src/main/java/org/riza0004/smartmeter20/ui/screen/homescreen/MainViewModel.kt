package org.riza0004.smartmeter20.ui.screen.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.riza0004.smartmeter20.util.Util

class MainViewModel: ViewModel() {
    val userFlow = Util.getUserFlow(viewModelScope)
}