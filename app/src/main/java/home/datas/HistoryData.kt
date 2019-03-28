package home.datas

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "HistoryTable")
data class HistoryData(
    @ColumnInfo(name = "dateTime") var dateTime: String, @ColumnInfo(name = "nhietDo") var nhietDo: String,
    @ColumnInfo(name = "doAm") var doAm: String, @ColumnInfo(name = "gas") var gas: String,
    @ColumnInfo(name = "camBienHongNgoai") var camBienHongNgoai: String, @ColumnInfo(name = "camBienTuCua") var camBienTuCua: String,
    @ColumnInfo(name = "den") var den: String, @ColumnInfo(name = "quat") var quat: String,
    @ColumnInfo(name = "vanTu") var vanTu: String, @ColumnInfo(name = "coiBao") var coiBao: String
)