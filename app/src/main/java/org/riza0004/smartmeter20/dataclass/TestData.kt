package org.riza0004.smartmeter20.dataclass

val testData = listOf(
    GroupDataClass(
        name = "group 1",
        items = listOf(
            SmartMeterDataClass(
                nama = "Smart Meter 1",
                isOn = false,
                energy = 25.5F,
                current = 0.155f,
                power = 0.25f,
                voltage = 200f,
                histories = emptyList()
            ),
            SmartMeterDataClass(
                nama = "Smart Meter 2",
                isOn = false,
                energy = 30F,
                current = 0.335f,
                power = 0.30f,
                voltage = 200f,
                histories = emptyList()
            ),
            SmartMeterDataClass(
                nama = "Smart Meter 3",
                isOn = false,
                energy = 50.5F,
                current = 0.41f,
                power = 0.5f,
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
                energy = 25.5F,
                current = 0.155f,
                power = 0.25f,
                voltage = 200f,
                histories = emptyList()
            ),
            SmartMeterDataClass(
                nama = "Smart Meter 2",
                isOn = false,
                energy = 30F,
                current = 0.335f,
                power = 0.30f,
                voltage = 200f,
                histories = emptyList()
            ),
            SmartMeterDataClass(
                nama = "Smart Meter 3",
                isOn = false,
                energy = 50.5F,
                current = 0.41f,
                power = 0.5f,
                voltage = 200f,
                histories = emptyList()
            ),
        )
    )
)
