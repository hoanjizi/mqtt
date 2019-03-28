package home.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import chinh.pham.mqtt.R
import chinh.pham.mqtt.databinding.ItemHistoryBinding
import home.datas.HistoryData
import home.viewmodels.HistoryViewModel
import java.util.*

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): HistoryHolder =
            HistoryHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_history, parent, false))

    override fun getItemCount(): Int = historys.size

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        holder.binding(historys[position])
    }

    private var historys = Collections.emptyList<HistoryData>()


    fun setListReceive(historys: List<HistoryData>) {
        this.historys = historys
        notifyDataSetChanged()

    }

    fun addListReceive(historyData: HistoryData) {
        this.historys.add(historyData)
        notifyDataSetChanged()
    }

    class HistoryHolder(itemHistoryBinding: ItemHistoryBinding) : RecyclerView.ViewHolder(itemHistoryBinding.root) {
        private val item = itemHistoryBinding
        fun binding(receiveData: HistoryData) {
            if (item.itemHistory == null) {
                item.itemHistory = HistoryViewModel(receiveData)
            } else {
                item.itemHistory?.setReceive(receiveData)
            }
            item.executePendingBindings()
        }
    }
}