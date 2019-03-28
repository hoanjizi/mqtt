package home.viewmodels

import android.databinding.BaseObservable
import home.datas.HistoryData

class HistoryViewModel(private var historyData: HistoryData) : BaseObservable() {
    fun setReceive(historyData: HistoryData) {
        this.historyData = historyData
    }

    fun getDateTime(): String = historyData.dateTime

    fun getNhietDo(): String {
        return historyData.nhietDo
    }

    fun getDoAm(): String {
        return historyData.doAm
    }

    fun getGas(): String {
        return historyData.gas
    }

    fun getCAmBienHongNgoai(): String {
        return historyData.camBienHongNgoai
    }

    fun getCAmBienTuCua(): String {
        return historyData.camBienTuCua
    }

    fun getDen(): String {
        return historyData.den
    }

    fun getQuat(): String {
        return historyData.quat
    }

    fun getVanTu(): String {
        return historyData.vanTu
    }

    fun getCoiBao(): String {
        return historyData.coiBao
    }
}