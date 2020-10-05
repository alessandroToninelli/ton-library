package com.toninelli.category

import java.lang.StringBuilder

data class TonResponseCategory(
    val list: List<TonServerCategory>
)

data class TonServerCategory(
    val ACDF1KEY: Int,
    val ACDF1DESC: String,
    val ACDF2CODIF: String,
    val ACDF2LIV: Int,
    val ACDF2LIV1: Int,
    val ACDF2LIV2: Int,
    val ACDF2LIV3: Int,
    val ACDF2LIV4: Int,
    val ACDF2LIV5: Int,
    val ACDF2DESC: String,
    val ACDF2NART: Int
)

object CategoryParser {


    fun parse(list: List<TonServerCategory>): List<TonCategory> {
        val tonCategoryList = mutableListOf<TonCategory>()
        list.forEach {
            val idBuilder = StringBuilder()

            for(i in (1..it.ACDF2LIV)){
                idBuilder.append(getLivNumber(i, it.ACDF2CODIF))
            }

            val id = idBuilder.toString()
            val parentId = id.substring(0, id.length -1)

            val category = TonCategory(id, it.ACDF2CODIF, it.ACDF2DESC, parentId, it.ACDF2NART)
             tonCategoryList.add(category)
        }

        return tonCategoryList
    }


    private fun getLivNumber(level: Int, codif: String): String {
        return when (level) {
            1 -> codif.substring(0, 2)
            2 -> codif.substring(3, 4)
            3 -> codif.substring(5, 6)
            4 -> codif.substring(7, 8)
            5 -> codif.substring(9)
            else -> error("Level out of bound")
        }
    }

}