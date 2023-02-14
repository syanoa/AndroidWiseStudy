package roomdatabase

import androidx.room.*
import com.wayne.algorithm.BaseApp
import roomdatabase.daos.CompanyDao
import roomdatabase.daos.UserDao

@Entity(tableName = "table_company")
data class CompanyEntiry(
    @PrimaryKey
    val cid:Int,
    @ColumnInfo(name="company_name")
    val name:String?
)
@Entity(tableName = "table_job")
data class JobEntity(
    @PrimaryKey
    val jid:Int,
    @ColumnInfo(name="job_name")
    val name:String?,
    @ColumnInfo(name="company_id")
    val cid:String?
)
@Entity(tableName = "table_user")
data class UserEntity(
    @PrimaryKey
    val uid:Int,
    @ColumnInfo(name = "user_name")
    val name:String?,
    @ColumnInfo(name="job_id")
    val jid:Int?)
@Database(entities = [CompanyEntiry::class, JobEntity::class, UserEntity::class], version = 1)
abstract class AppDatabase :RoomDatabase(){
   abstract fun companyDao():CompanyDao
   abstract fun jobDao():CompanyDao
   abstract fun userDao():UserDao
   private fun initialize() = Room.databaseBuilder(BaseApp.instance,AppDatabase::class.java,"wayne_database")
   val mInstance by lazy {
       initialize()
   }

//    companion object{
//        @Volatile
//        private var mInstance:AppDatabase? = null
//        val instance
//        get() = mInstance?: synchronized(this){
//            Room.databaseBuilder(BaseApp.instance,AppDatabase::class.java,"wayne_database")
//        }
//    }
}