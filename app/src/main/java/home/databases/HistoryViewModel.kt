package home.databases

import android.app.Application
import home.datas.DaoHistory
import home.datas.HistoryData

class HistoryViewModel(app: Application) {
    private val db = DatabaseMqtt.getInstance(app)
    private var daoHusky: DaoHistory? = null

    init {
        daoHusky = db!!.historyDao()
    }

    suspend fun getAllHusky(): List<HistoryData>? {
        return if (daoHusky != null) {
            daoHusky!!.getHistoryList()
        } else {
            null
        }
    }
    fun insert(husky: HistoryData){

    }
    fun deleteAllTable(){
    }

}