package org.riza0004.smartmeter20.dataclass

data class SmartMeterDataClass(
    val nama: String,
    val isOn: Boolean,
    val energy: Float,
    val current: Float,
    val power: Float,
    val voltage: Float,
    val histories: List<HistoryDataClass>
)
