package hr.algebra.swimapp.model

import java.time.LocalTime

data class SwimInfo(val dayOfWeek: String, val laps: Int, val time: LocalTime, val distance: Int)