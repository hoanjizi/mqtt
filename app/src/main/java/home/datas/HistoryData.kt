package home.datas

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "HistoryTable")
data class HistoryData(
    @PrimaryKey(autoGenerate = true) @NonNull var id: Int,
    @ColumnInfo(name = "dateTime") var dateTime: String, @ColumnInfo(name = "nhietDo") var nhietDo: String,
    @ColumnInfo(name = "doAm") var doAm: String, @ColumnInfo(name = "gas") var gas: String,
    @ColumnInfo(name = "camBienHongNgoai") var camBienHongNgoai: String, @ColumnInfo(name = "camBienTuCua") var camBienTuCua: String,
    @ColumnInfo(name = "den") var den: String, @ColumnInfo(name = "quat") var quat: String,
    @ColumnInfo(name = "vanTu") var vanTu: String, @ColumnInfo(name = "coiBao") var coiBao: String
)