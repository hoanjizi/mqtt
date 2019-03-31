package home.databases

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import home.datas.DaoHistory
import home.datas.HistoryData

class HistoryRepository(app : Application) {
    private val db = DatabaseMqtt.getInstance(app)
    private var daoHistory: DaoHistory? = db?.historyDao()

    fun getAllHistory(): LiveData<List<HistoryData>>? {
        return if (daoHistory != null) {
            daoHistory?.getHistoryList()
        } else {
            null
        }
    }
    fun insertHistory(history : HistoryData) {
        InsertAsyncTask(daoHistory!!).execute()
    }

    fun deleteAllTable(){
        daoHistory!!.deleteAll()
    }
    class InsertAsyncTask(daoHistory: DaoHistory) : AsyncTask<HistoryData, Void, Void>() {
        private var mAsyncDaoHusky: DaoHistory? = null

        init{
            mAsyncDaoHusky = daoHistory
        }
        override fun doInBackground(vararg word: HistoryData): Void? {
            mAsyncDaoHusky?.insertHistory(word[0])
            return null
        }

    }
}