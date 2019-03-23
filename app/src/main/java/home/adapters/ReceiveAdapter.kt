package home.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import chinh.pham.mqtt.R
import chinh.pham.mqtt.databinding.ItemReceiveDataBinding
import home.datas.ReceiveData
import home.viewmodels.ReceiveDataViewModel
import java.util.*

class ReceiveAdapter : RecyclerView.Adapter<ReceiveAdapter.ReceiveHolder>() {

    private var receives = Collections.emptyList<ReceiveData>()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ReceiveHolder {
        return ReceiveHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_receive_data, parent, false))
    }

    override fun getItemCount(): Int  = receives.size

    override fun onBindViewHolder(holder: ReceiveHolder, p: Int) {
        holder.binding(receives[p])
    }

    fun setListReceive(receives: List<ReceiveData>) {
        this.receives = receives
        notifyDataSetChanged()

    }

    fun addListReceive(receiveData: ReceiveData) {
        this.receives.add(receiveData)
        notifyDataSetChanged()
    }

    class ReceiveHolder(itemReceiveDataBinding: ItemReceiveDataBinding) : RecyclerView.ViewHolder(itemReceiveDataBinding.root) {
        private val item = itemReceiveDataBinding
        fun binding(receiveData: ReceiveData) {
            if (item.itemReceive == null) {
                item.itemReceive = ReceiveDataViewModel(receiveData)
            } else {
                item.itemReceive?.setReceive(receiveData)
            }
            item.executePendingBindings()
        }
    }
}