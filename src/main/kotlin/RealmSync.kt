import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.AppConfiguration
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.subscriptions
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import java.util.*

open class Item(
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

suspend fun main()
{
    val username = "itbgomez"
    val password = "dFis93A1773w8Mlk"

    val realmApp = App.create(AppConfiguration.Builder("application-0-rquuh")
        .log(LogLevel.ALL)
        .build())

    //realmApp.emailPasswordAuth.registerUser(username, password)

    val creds = Credentials.emailPassword(username, password)
    realmApp.login(creds)
    val user = realmApp.currentUser!!

    val config = SyncConfiguration.Builder(user, setOf(Item::class))
        .initialSubscriptions { realm ->
            add(
                realm.query<Item>(),
                "All Items"
            )
//      add(
//          realm.query<Item>("owner_id == $0", realmApp.currentUser!!.id),
//          "User's Items"
//      )
        }
        .waitForInitialRemoteData()
        .build()
    println("Realm Path: ${config.path}")
    val realm = Realm.open(config)

    realm.subscriptions.waitForSynchronization()

    realm.writeBlocking {
        val item = Item(summary= "Some summary ${Date()}", owner_id = user.id)
        copyToRealm(item)
    }

    val items = realm.query<Item>().find()
    items.forEach { println(it.summary) }
    println(items.size)
}