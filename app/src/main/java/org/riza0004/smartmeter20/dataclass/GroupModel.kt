package org.riza0004.smartmeter20.dataclass

data class GroupModel(
    val name: String
){
    constructor() : this("")

    companion object{
        const val COLLECTION = "group"
    }
}

