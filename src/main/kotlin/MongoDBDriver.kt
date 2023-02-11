/*import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.MongoClientSettings.getDefaultCodecRegistry
import com.mongodb.ServerApi
import com.mongodb.ServerApiVersion
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.bson.codecs.configuration.CodecRegistries.fromProviders
import org.bson.codecs.configuration.CodecRegistries.fromRegistries
import org.bson.codecs.pojo.PojoCodecProvider
import org.bson.types.ObjectId

data class ItemRealm2(
    var _id: ObjectId = ObjectId.get(),
    var complete: Boolean = false,
    var summary: String = "",
    var owner_id: String = "")

fun main()
{
    val user = "itbgomez"
    val password = "dFis93A1773w8Mlk"

    val pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build()
    val pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider))
    val connectionString =
        ConnectionString("mongodb+srv://$user:$password@cluster0.yxp8trs.mongodb.net/?retryWrites=true&w=majority")
    val settings: MongoClientSettings = MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .serverApi(
            ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build()
        )
        .build()
    val mongoClient: MongoClient = MongoClients.create(settings)

    val database: MongoDatabase = mongoClient.getDatabase("todo")
        .withCodecRegistry(pojoCodecRegistry);
    val collection: MongoCollection<ItemRealm2> = database.getCollection("ItemRealm2", ItemRealm2::class.java)

    val item = ItemRealm2(summary = "ITB")
    collection.insertOne(item)
}*/