package com.toninelli.category

import java.math.BigInteger
import javax.management.monitor.StringMonitor

data class TonCategory(
    val id: String,
    val key: String,
    val name: String,
    val parent: String,
    val nart: Int
)