package home.datas

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DaoHistory {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: HistoryData)

    @Query("DELETE FROM HistoryTable")
    suspend fun deleteAll()

    @Query("SELECT * FROM HistoryTable")
    suspend fun getHistoryList(): List<HistoryData>?
}