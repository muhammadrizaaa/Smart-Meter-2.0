package org.riza0004.smartmeter20.dataclass

val testData = listOf(
    GroupDataClass(
        name = "group 1",
        items = listOf(
            SmartMeterDataClass(
                nama = "Smart Meter 1",
                isOn = false,
                energy = 0.0025F,
                current = 0.155f,
                power = 25.5f,
                voltage = 200f,
                histories = emptyList()
            ),
            SmartMeterDataClass(
                nama = "Smart Meter 2",
                isOn = false,
                energy = 0.003F,
                current = 0.335f,
                power = 30f,
                voltage = 200f,
                histories = emptyList()
            ),
            SmartMeterDataClass(
                nama = "Smart Meter 3",
                isOn = false,
                energy = 0.05F,
                current = 0.41f,
                power = 5f,
                voltage = 200f,
                histories = emptyList()
            ),
        )
    ),
    GroupDataClass(
        name = "group 2",
        items = listOf(
            SmartMeterDataClass(
                nama = "Smart Meter 1",
                isOn = false,
                energy = 0.0025F,
                current = 0.155f,
                power = 25.5f,
                voltage = 200f,
                histories = emptyList()
            ),
            SmartMeterDataClass(
                nama = "Smart Meter 2",
                isOn = false,
                energy = 0.003F,
                current = 0.335f,
                power = 30f,
                voltage = 200f,
                histories = emptyList()
            ),
            SmartMeterDataClass(
                nama = "Smart Meter 3",
                isOn = false,
                energy = 0.05F,
                current = 0.41f,
                power = 5f,
                voltage = 200f,
                histories = emptyList()
            ),
        )
    )
)
