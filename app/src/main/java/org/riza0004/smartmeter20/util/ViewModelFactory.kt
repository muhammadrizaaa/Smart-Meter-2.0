package org.riza0004.smartmeter20.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseUser
import org.riza0004.smartmeter20.ui.screen.detailgroup.DetailGroupViewModel
import org.riza0004.smartmeter20.ui.screen.homescreen.HomeViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val user: FirebaseUser): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(user) as T
        }
        else if(modelClass.isAssignableFrom(DetailGroupViewModel::class.java)){
            return DetailGroupViewModel(user) as T
        }
        throw IllegalArgumentException("Unknown VM")
    }
}