package com.toninelli.category

infix fun Int.downToExclusive(other: Int): IntProgression =  IntProgression.fromClosedRange(this, other +1, -1)