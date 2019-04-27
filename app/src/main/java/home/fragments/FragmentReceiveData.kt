package home.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import chinh.pham.mqtt.R
import com.google.gson.Gson
import home.HomeActivity
import home.adapters.ReceiveAdapter
import home.datas.HistoryData
import home.datas.ReceiveData
import kotlinx.android.synthetic.main.fragment_receive_data.*
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.IMqttActionListener
import org.eclipse.paho.client.mqttv3.IMqttToken
import org.eclipse.paho.client.mqttv3.MqttException
import utils.FragmentUtil
import utils.dataLocalRoom
import utils.hideKeyBoard
import java.text.SimpleDateFormat
import java.util.*

class FragmentReceiveData : Fragment() {
    var mqttClient: MqttAndroidClient? = null
    private var edtTopicRead: EditText? = null
    private var btnTopicRead: TextView? = null
    private var btnViewHistory: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_receive_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            (it as HomeActivity).unLockDrawer()
            btnTopicRead = it.findViewById(R.id.btnTopicRead)
            edtTopicRead = it.findViewById(R.id.edtTopicRead)
            btnViewHistory = it.findViewById(R.id.viewAllHistory)
        }
        val adapter = ReceiveAdapter()
        val gson = Gson()
        rvReceiveData.apply {
            layoutManager = LinearLayoutManager(context)
            setItemViewCacheSize(100)
            setHasFixedSize(false)
        }
        rvReceiveData.adapter = adapter
        val list = ArrayList<ReceiveData>()
        adapter.setListReceive(list)

        showDrawer.setOnClickListener {
            activity?.let {
                (it as HomeActivity).openDrawer()
            }
        }
        btnViewHistory?.let { btn ->
            btn.setOnClickListener {
                activity?.let {
                    (it as HomeActivity).onReplaceFragmentWithHistoryEvent(FragmentUtil.fragmentReceive,
                            FragmentUtil.fragmentHistory,FragmentHistory())
                    it.closeDrawer()
                }
            }
        }
        btnSendData.setOnClickListener {
            activity?.let { home ->
                val sendFragment = FragmentSendData()
                sendFragment.mqttClient = mqttClient
                (home as HomeActivity).xmlClick = sendFragment
                home.onReplaceFragmentWithHistoryEvent(FragmentUtil.fragmentReceive,FragmentUtil.fragmentSendData,sendFragment)

            }
        }

        btnTopicRead?.let { text ->
            text.setOnClickListener {
                activity?.let { home ->
                    (home as HomeActivity).closeDrawer()
                    hideKeyBoard(home)
                }

                mqttClient?.let { mqtt ->
                    try {
                        val topicRead = edtTopicRead?.text.toString()
                        mqtt.subscribe(topicRead, 0, null, object : IMqttActionListener {
                            override fun onSuccess(asyncActionToken: IMqttToken?) {
                                txtShowSub.text = getString(R.string.topic_connected)
                                txtSub.visibility = View.VISIBLE
                                txtSub.text = topicRead
                                mqtt.subscribe(topicRead, 0) { topic, message ->
                                    activity?.let {
                                        Log.e("AAAAA", topic)
                                        val receiveData = gson.fromJson(message.toString(), ReceiveData::class.java)
                                        it.runOnUiThread {
                                            adapter.addListReceive(receiveData)
                                            saveData(receiveData)
                                        }
                                    }

                                }
                            }

                            override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                                txtShowSub.text = getString(R.string.topic_doesn_t_connect)
                                txtSub.visibility = View.INVISIBLE
                            }

                        })

                    } catch (ex: MqttException) {
                        Log.e("AAAAA", ex.printStackTrace().toString())
                        txtShowSub.text = getString(R.string.topic_doesn_t_connect)
                        txtSub.visibility = View.INVISIBLE
                    }
                }
            }
        }

    }

    @SuppressLint("SimpleDateFormat")
    private fun saveData(receiveData : ReceiveData) {
        val df = SimpleDateFormat("dd MM yyyy 'at' HH:mm:ss")
        val time = df.format(Calendar.getInstance().time)
        val historyData = HistoryData(0,time,receiveData.nhietDo,receiveData.doAm,
            receiveData.gas,receiveData.camBienHongNgoai,receiveData.camBienTuCua,
            receiveData.den,receiveData.quat,receiveData.vanTu, receiveData.coiBao
            )
        dataLocalRoom.insert(historyData)
    }
}