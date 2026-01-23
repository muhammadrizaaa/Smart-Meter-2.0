package org.riza0004.smartmeter20.dataclass

data class UserModel(
    val email: String,
    val displayName: String
){
    constructor() : this("", "")

    companion object{
        const val COLLECTION = "users"
    }
}
