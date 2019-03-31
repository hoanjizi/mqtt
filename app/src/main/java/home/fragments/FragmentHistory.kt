package home.fragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chinh.pham.mqtt.R
import home.adapters.HistoryAdapter
import kotlinx.android.synthetic.main.fragment_history.*
import utils.dataLocalRoom

class FragmentHistory : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = HistoryAdapter()
        rvHistory.apply {
            layoutManager = LinearLayoutManager(context)
            setItemViewCacheSize(100)
            setHasFixedSize(false)
            setAdapter(adapter)
        }
        activity?.let { it ->
            dataLocalRoom.getAllHistory()?.observe(it, Observer { listHistory->
                listHistory?.let { listNotNull ->
                    adapter.setListReceive(listNotNull)
                }
            })
        }
    }
}