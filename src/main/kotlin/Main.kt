
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.toninelli.category.TonCategory
import com.toninelli.category.TonCategoryTree

fun main() {

    val a = ::main.javaClass.getResourceAsStream("categ_test.json")
    val json = a.bufferedReader().readText()

    val type = object : TypeToken<List<TonCategory>>(){}.type

    val list = Gson().fromJson<List<TonCategory>>(json, type)

    val categoryTree = TonCategoryTree(list)

    categoryTree.getSubCateg("5403010000").forEach {
       println(it)
    }
}