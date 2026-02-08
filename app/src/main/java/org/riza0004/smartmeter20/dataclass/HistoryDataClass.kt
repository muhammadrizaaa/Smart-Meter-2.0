package org.riza0004.smartmeter20.dataclass

import com.google.firebase.Timestamp

data class HistoryDataClass(
    val timestamp: Timestamp?,
    val current: Float,
    val energy: Float,
    val power: Float,
    val voltage: Float
){
    constructor() : this(null, 0.0F, 0.0F, 0.0F, 0.0F)

    companion object{
        const val COLLECTION = "history"
    }
}
