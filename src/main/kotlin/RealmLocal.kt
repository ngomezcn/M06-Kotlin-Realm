/*import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import java.util.*
fun main()
{
    val config = RealmConfiguration.Builder(setOf(ItemRealm2::class))
        .deleteRealmIfMigrationNeeded()
        // .directory("customPath")
        .build()
    println("Realm Path: ${config.path}")
    val realm = Realm.open(config)

    realm.writeBlocking {
        val itemRealm = ItemRealm2(summary = "Some summary ${Date()}"
            // doAfter = query<Item>().find().take(2).toRealmList()
        )
        copyToRealm(itemRealm)
    }

    val items = realm.query<ItemRealm2>().find()
    items.forEach { println(it.summary) }
    println(items.size)
}*/