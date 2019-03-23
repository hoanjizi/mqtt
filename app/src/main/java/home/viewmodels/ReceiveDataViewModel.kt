package home.viewmodels

import home.datas.ReceiveData

class ReceiveDataViewModel(private var receiveData: ReceiveData) {

    fun setReceive(receiveData: ReceiveData) {
        this.receiveData = receiveData
    }

    fun getNhietDo(): String {
        return receiveData.nhietDo
    }

    fun getDoAm(): String {
        return receiveData.doAm
    }

    fun getGas(): String {
        return receiveData.gas
    }

    fun getCAmBienHongNgoai(): String {
        return receiveData.camBienHongNgoai
    }

    fun getCAmBienTuCua(): String {
        return receiveData.camBienTuCua
    }

    fun getDen(): String {
        return receiveData.den
    }

    fun getQuat(): String {
        return receiveData.quat
    }

    fun getVanTu(): String {
        return receiveData.vanTu
    }

    fun getCoiBao(): String {
        return receiveData.coiBao
    }


}