package org.riza0004.smartmeter20.dataclass

data class HistoryDataClass(
    val timeStamps: String,
    val current: Float,
    val energy: Float,
    val power: Float,
    val voltage: Float
)
