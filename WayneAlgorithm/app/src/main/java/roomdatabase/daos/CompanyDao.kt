package roomdatabase.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import roomdatabase.CompanyEntiry

@Dao
interface CompanyDao {
    @Query("SELECT * FROM table_company")
    suspend fun allCompanies():List<CompanyEntiry>

    @Query("SELECT * FROM table_company WHERE cid=:cid")
    fun getCompanyByCid(cid:String):CompanyEntiry

    @Insert
    fun insertCompanies(vararg companies:CompanyEntiry)

    @Delete
    fun deleteCompany(target:CompanyEntiry)
}