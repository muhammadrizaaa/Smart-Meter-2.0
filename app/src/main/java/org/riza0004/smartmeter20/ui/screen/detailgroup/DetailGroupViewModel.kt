package org.riza0004.smartmeter20.ui.screen.detailgroup

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import org.riza0004.smartmeter20.dataclass.GroupModel
import org.riza0004.smartmeter20.dataclass.SmartMeterModel
import org.riza0004.smartmeter20.dataclass.UserModel

class DetailGroupViewModel(private val user: FirebaseUser): ViewModel() {
    private val db = Firebase.firestore
    var dataId = mutableStateListOf<String>()
        private set
    var data = mutableStateListOf<SmartMeterModel>()
        private set
    private var registration: ListenerRegistration? = null

    private val  listener = object: EventListener<QuerySnapshot>{
        override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
            if(error != null){
                Log.e("MainViewModel", "onEvent:error", error)
            }
            value?.documentChanges?.forEach { handle(it) }
        }
    }

    fun handle(change: DocumentChange){
        when(change.type){
            DocumentChange.Type.ADDED->{
                val smartMeter = change.document.toObject<SmartMeterModel>()
                dataId.add(change.newIndex, change.document.id)
                data.add(change.newIndex, smartMeter)
            }
            DocumentChange.Type.MODIFIED->{
                val smartMeter = change.document.toObject<SmartMeterModel>()
                if(change.oldIndex == change.newIndex){
                    data[change.oldIndex] =smartMeter
                }
                else{
                    dataId.removeAt(change.oldIndex)
                    dataId.add(change.newIndex, change.document.id)
                    data.removeAt(change.oldIndex)
                    data.add(change.newIndex, smartMeter)
                }
            }
            DocumentChange.Type.REMOVED->{
                dataId.removeAt(change.oldIndex)
                data.removeAt(change.oldIndex)
            }
        }
    }

    fun init(groupId: String) {
        dataId.clear()
        data.clear()
        registration = db.collection(UserModel.COLLECTION)
            .document(user.uid)
            .collection(GroupModel.COLLECTION)
            .document(groupId)
            .collection(SmartMeterModel.COLLECTION)
            .addSnapshotListener(listener)
    }

    override fun onCleared() {
        registration?.remove()
        registration = null
        super.onCleared()
    }
}