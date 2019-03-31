package home.databases

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import home.datas.HistoryData

class HistoryViewModel(app: Application) : AndroidViewModel(app) {
    private var historyRepository: HistoryRepository? = null

    init {
        historyRepository = HistoryRepository(app)
    }

    fun insert(husky: HistoryData) {
        historyRepository!!.insertHistory(husky)
    }

    fun getAllHistory(): LiveData<List<HistoryData>>? {
        return historyRepository!!.getAllHistory()
    }

    fun deleteAllTable() {
        historyRepository!!.deleteAllTable()
    }

}