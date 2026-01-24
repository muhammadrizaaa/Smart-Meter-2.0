package org.riza0004.smartmeter20.dataclass

data class SmartMeterModel(
    val name: String
){
    constructor() : this("")

    companion object{
        const val COLLECTION = "smart_meter"
    }
}
