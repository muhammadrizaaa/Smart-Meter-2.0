package org.riza0004.smartmeter20.dataclass

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class MeterLiveData(
    val voltage: Double = 0.0,
    val current: Double = 0.0,
    val power: Double = 0.0,
    val energy: Double = 0.0,
    val ison: Long = 0L
)
