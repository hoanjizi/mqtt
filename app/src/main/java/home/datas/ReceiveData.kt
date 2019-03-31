package home.datas

import com.google.gson.annotations.SerializedName

data class ReceiveData(@SerializedName("nhietdo") private val _nhietDo: String?, @SerializedName("doam") private val _doAm: String?,
                       @SerializedName("gas")private val _gas: String?, @SerializedName("hongngoai")private val _camBienHongNgoai: String?,
                       @SerializedName("tucua")private val _camBienTuCua: String?, @SerializedName("den")private val _den: String?,
                       @SerializedName("quat")private val _quat: String?, @SerializedName("vantu")private val _vanTu: String?,
                       @SerializedName("coibao")private val _coiBao: String?) {
    val nhietDo
    get() = _nhietDo ?: "N/a"

    val doAm
    get() = _doAm ?: "N/a"

    val gas
    get() = _gas ?: "N/a"

    val camBienHongNgoai
    get() = _camBienHongNgoai ?: "N/a"

    val camBienTuCua
    get() = _camBienTuCua ?: "N/a"

    val den
    get() = _den ?: "N/a"

    val quat
    get() = _quat ?: "N/a"

    val vanTu
    get() = _vanTu ?: "N/a"

    val coiBao
    get() = _coiBao ?: "N/a"

}