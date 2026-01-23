package org.riza0004.smartmeter20.ui.screen.homescreen

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
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import org.riza0004.smartmeter20.dataclass.GroupModel
import org.riza0004.smartmeter20.dataclass.UserModel

class HomeViewModel(private val user: FirebaseUser): ViewModel() {
    private val db = Firebase.firestore
    var data = mutableStateListOf<GroupModel>()
        private set
    private var registration: ListenerRegistration? = null
    private val listener = object : EventListener<QuerySnapshot> {
        override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?){
            if(error != null){
                Log.e("HomeViewModel", "onEvent Err: ", error)
                return
            }
            value?.documentChanges?.forEach { handle(it) }
        }
    }

    init {
        upsertUser()
        registration =  db.collection(UserModel.COLLECTION)
            .document(user.uid)
            .collection(GroupModel.COLLECTION)
            .addSnapshotListener(listener)
    }

    private fun handle(change: DocumentChange){
        when(change.type){
            DocumentChange.Type.ADDED -> {
                val group = change.document.toObject<GroupModel>()
                data.add(change.newIndex, group)
            }
            DocumentChange.Type.MODIFIED ->{
                val group = change.document.toObject<GroupModel>()
                if(change.oldIndex == change.newIndex){
                    data[change.oldIndex] = group
                }
                else{
                    data.removeAt(change.oldIndex)
                    data.add(change.newIndex, group)
                }
            }
            DocumentChange.Type.REMOVED -> {
                data.removeAt(change.oldIndex)
            }
        }
    }

    fun upsertUser(){
        val userDoc = db.collection(UserModel.COLLECTION).document(user.uid)
        val userData = UserModel(
            email = user.email.toString(),
            displayName = user.displayName.toString()
        )
        userDoc.set(userData, SetOptions.merge())
    }

    fun insertGroup(name: String){
        db.collection(UserModel.COLLECTION)
            .document(user.uid)
            .collection(GroupModel.COLLECTION)
            .add(
                GroupModel(
                    name = name
                )
            )
    }

    override fun onCleared() {
        registration?.remove()
        registration = null
        super.onCleared()
    }
}