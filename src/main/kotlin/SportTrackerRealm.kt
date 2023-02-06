import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import java.util.*
import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.io.path.writeText


open class Workouts(
    @PrimaryKey
    var _id: ObjectId = ObjectId.create(),
    var nom : String = "",
    var duracio : Int = 0
) : RealmObject {
    // Declaring empty contructor
    constructor() : this(nom = "") {}
    //var doAfter: RealmList<Item>? = realmListOf()
    override fun toString() = "Item($_id, $nom)"
}

fun main() {
    val scanner = Scanner(System.`in`)

    val config = RealmConfiguration.Builder(setOf(Workouts::class))
        .deleteRealmIfMigrationNeeded()
        // .directory("customPath")
        .build()
    println("Realm Path: ${config.path}")
    val realm = Realm.open(config)

    while (true) {
        println("Voleu enregistrar dades? si no")
        if(scanner.nextLine() != "si") {
            break
        }

        print("Esport: "); val name = scanner.nextLine().lowercase()
        print("Duration: "); val duration = scanner.nextLine().toInt()


        val items = realm.query<Workouts>("nom == $name").find()

        println(items)

        //items.forEach { println(it.summary) }

        /*realm.writeBlocking {
            val workouts = Workouts().apply {
                nom = name;
                duracio = duration
                // doAfter = query<Item>().find().take(2).toRealmList()
            }
            copyToRealm(workouts)
        }*/



        /*transaction {
            addLogger(StdOutSqlLogger)

            val query : Query = Workouts.select(Workouts.nom.lowerCase() eq name)

            if (query.empty()) {
                Workouts.insert {
                    it[Workouts.nom] = name
                    it[Workouts.duracio] = duration
                }
            } else
            {
                query.forEach {
                        ib ->
                    Workouts.update({ Workouts.nom.lowerCase() eq name }) {
                        it[Workouts.nom] = name
                        it[Workouts.duracio] = duration + ib[duracio]
                    }
                }
            }
        }*/

        println("Enregistrat")
    }

    /*println("[Esport] [Duracio]")
    transaction {
        val query = Workouts.selectAll()

        var totalTime = 0
        query.forEach {
            totalTime += it[Workouts.duracio]
            println("${it[Workouts.nom]} - ${it[Workouts.duracio]}")
        }
        println("Temps total: $totalTime")
    }*/
}


