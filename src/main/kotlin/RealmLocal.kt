import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import java.util.*

open class ItemRealm(
    @PrimaryKey
    var _id: ObjectId = ObjectId.create(),
    var complete: Boolean = false,
    var summary: String = "",
    var owner_id: String = ""
) : RealmObject {
    // Declaring empty contructor
    constructor() : this(owner_id = "") {}
    //var doAfter: RealmList<Item>? = realmListOf()
    override fun toString() = "Item($_id, $summary)"
}

fun main()
{
    val config = RealmConfiguration.Builder(setOf(ItemRealm::class))
        .deleteRealmIfMigrationNeeded()
        // .directory("customPath")
        .build()
    println("Realm Path: ${config.path}")
    val realm = Realm.open(config)

    realm.writeBlocking {
        val itemRealm = ItemRealm(summary = "Some summary ${Date()}"
            // doAfter = query<Item>().find().take(2).toRealmList()
        )
        copyToRealm(itemRealm)
    }

    val items = realm.query<ItemRealm>().find()
    items.forEach { println(it.summary) }
}