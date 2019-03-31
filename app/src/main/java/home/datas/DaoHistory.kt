package home.datas

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface DaoHistory {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHistory(history: HistoryData)

    @Query("DELETE FROM HistoryTable")
    fun deleteAll()

    @Query("SELECT * FROM HistoryTable ORDER BY dateTime DESC")
    fun getHistoryList(): LiveData<List<HistoryData>>?
}