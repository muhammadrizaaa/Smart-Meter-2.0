package org.riza0004.smartmeter20.dataclass

data class GroupDataClass(
    val name: String,
    val items: List<SmartMeterDataClass>,
    val isOn: Boolean = items.any { it.isOn },
    val energy : Float = items.sumOf { it.voltage.toDouble() }.toFloat(),
    val current: Float = items.sumOf { it.current.toDouble() }.toFloat(),
    val power: Float = items.sumOf { it.power.toDouble() }.toFloat(),
    val voltage: Float = items.sumOf { it.voltage.toDouble() }.toFloat()
)
