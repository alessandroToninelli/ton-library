import com.toninelli.category.CategoryParser
import com.toninelli.category.TonCategoryTree
import com.toninelli.category.TonServerCategory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun main() {

    val a = ::main.javaClass.getResourceAsStream("categ_test.json")
    val json = a.bufferedReader().readText()

    val type = object : TypeToken<List<TonServerCategory>>(){}.type

    val list = Gson().fromJson<List<TonServerCategory>>(json, type)


    val parsedList = CategoryParser.parse(list)

    val categoryTree = TonCategoryTree(parsedList)

    categoryTree.getSubCateg("59").forEach {
       println(it)
    }
}