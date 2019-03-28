package home.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import home.datas.DaoHistory
import home.datas.HistoryData

@Database(entities = [HistoryData::class], version = 1,exportSchema = false)
abstract class DatabaseMqtt : RoomDatabase() {
    abstract fun historyDao() : DaoHistory
    companion object {
        private var INSTANCE: DatabaseMqtt? = null
        fun getInstance(context: Context): DatabaseMqtt? {
            if (INSTANCE == null) {
                synchronized(DatabaseMqtt::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, DatabaseMqtt::class.java, "mqtt.db").allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}