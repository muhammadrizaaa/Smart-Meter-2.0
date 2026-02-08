package org.riza0004.smartmeter20.ui.screen.usagereportmain

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.riza0004.smartmeter20.dataclass.GroupModel
import org.riza0004.smartmeter20.dataclass.HistoryDataClass
import org.riza0004.smartmeter20.dataclass.MeterLiveData
import org.riza0004.smartmeter20.dataclass.SmartMeterModel
import org.riza0004.smartmeter20.dataclass.UserModel

class UsageReportViewModel(
    private val user: FirebaseUser
): ViewModel() {
    private val db = Firebase.firestore
    var dataId = mutableStateListOf<String>()
        private set
    var data = mutableStateListOf<HistoryDataClass>()
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
    //rtdb
    private val _liveData = MutableStateFlow<MeterLiveData?>(null)
    val liveData = _liveData.asStateFlow()
    private var listenerRealtime: ValueEventListener? = null
    private var ref: DatabaseReference? = null

    fun handle(change: DocumentChange){
        when(change.type){
            DocumentChange.Type.ADDED->{
                val smartMeter = change.document.toObject<HistoryDataClass>()
                dataId.add(change.newIndex, change.document.id)
                data.add(change.newIndex, smartMeter)
            }
            DocumentChange.Type.MODIFIED->{
                val smartMeter = change.document.toObject<HistoryDataClass>()
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

    fun init(groupId: String, meterId: String) {
        dataId.clear()
        data.clear()
        registration = db.collection(UserModel.COLLECTION)
            .document(user.uid)
            .collection(GroupModel.COLLECTION)
            .document(groupId)
            .collection(SmartMeterModel.COLLECTION)
            .document(meterId)
            .collection(HistoryDataClass.COLLECTION)
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener(listener)
    }

    fun observeMeter(meterId: String){
        ref = FirebaseDatabase.getInstance()
            .getReference(meterId)

        listenerRealtime = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                _liveData.value = snapshot.getValue(MeterLiveData::class.java)
            }

            override fun onCancelled(err: DatabaseError) {
                Log.e("RTDB", err.message)
            }
        }
        ref?.addValueEventListener(listenerRealtime!!)
    }

    fun setRelay(meterId: String, value: Long){
        val masterRef = FirebaseDatabase.getInstance()
            .getReference(meterId)
        masterRef.child("ison").setValue(value)
    }

    override fun onCleared() {
        registration?.remove()
        registration = null
        listenerRealtime?.let { ref?.removeEventListener(it) }
        super.onCleared()
    }
}