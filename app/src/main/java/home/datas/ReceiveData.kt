package home.datas

import com.google.gson.annotations.SerializedName

data class ReceiveData(@SerializedName("nhietdo") var nhietDo: String, @SerializedName("doam") var doAm: String,
                       @SerializedName("gas") var gas: String, @SerializedName("hongngoai") var camBienHongNgoai: String,
                       @SerializedName("tucua") var camBienTuCua: String, @SerializedName("den") var den: String,
                       @SerializedName("quat") var quat: String, @SerializedName("vantu") var vanTu: String,
                       @SerializedName("coibao") var coiBao: String)