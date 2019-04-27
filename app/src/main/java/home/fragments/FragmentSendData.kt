package home.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import chinh.pham.mqtt.R
import kotlinx.android.synthetic.main.fragment_send_data.*
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.MqttMessage
import utils.ClickInterface


class FragmentSendData : Fragment(), ClickInterface {
    var mqttClient: MqttAndroidClient? = null
    var topic = ""
    private var encodedOn = ByteArray(0)
    var encodedOff = ByteArray(0)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_send_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topic = edtTopicWrite.text.toString()
    }

    override fun onClickRbtn(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked
            topic = edtTopicWrite.text.toString()
            when (view.id) {
                R.id.onDen -> {
                    if (checked) {
                        try {
                            encodedOn = "denON".toByteArray(Charsets.UTF_8)
                            val mes = MqttMessage(encodedOn).apply {
                                isRetained = true
                            }
                            mqttClient?.let {
                                it.publish(topic, mes)
                            }
                        } catch (e: Exception) {
                            Toast.makeText(activity!!, "không thể kết nối tới topic", Toast.LENGTH_LONG).show()
                        }
                    }
                }
                R.id.offDen -> {
                    try {
                        encodedOff = "denOFF".toByteArray(Charsets.UTF_8)
                        val mes = MqttMessage(encodedOff).apply {
                            isRetained = true
                        }
                        mqttClient?.let {
                            it.publish(topic, mes)
                        }
                    } catch (e: Exception) {
                        Toast.makeText(activity!!, "không thể kết nối tới topic", Toast.LENGTH_LONG).show()
                    }
                }
                R.id.onQuat -> {
                    if (checked) {
                        try {
                            encodedOn = "quatON".toByteArray(Charsets.UTF_8)
                            val mes = MqttMessage(encodedOn).apply {
                                isRetained = true
                            }
                            mqttClient?.let {
                                it.publish(topic, mes)
                            }
                        } catch (e: Exception) {
                            Toast.makeText(activity!!, "không thể kết nối tới topic", Toast.LENGTH_LONG).show()
                        }
                    }
                }
                R.id.offQuat -> {
                    try {
                        encodedOff = "quatOFF".toByteArray(Charsets.UTF_8)
                        val mes = MqttMessage(encodedOff).apply {
                            isRetained = true
                        }
                        mqttClient?.let {
                            it.publish(topic, mes)
                        }
                    } catch (e: Exception) {
                        Toast.makeText(activity!!, "không thể kết nối tới topic", Toast.LENGTH_LONG).show()
                    }
                }
                R.id.onBom -> {
                    if (checked) {
                        try {
                            encodedOn = "bomON".toByteArray(Charsets.UTF_8)
                            val mes = MqttMessage(encodedOn).apply {
                                isRetained = true
                            }
                            mqttClient?.let {
                                it.publish(topic, mes)
                            }
                        } catch (e: Exception) {
                            Toast.makeText(activity!!, "không thể kết nối tới topic", Toast.LENGTH_LONG).show()
                        }
                    }
                }
                R.id.offBom -> {
                    try {
                        encodedOff = "bomOFF".toByteArray(Charsets.UTF_8)
                        val mes = MqttMessage(encodedOff).apply {
                            isRetained = true
                        }
                        mqttClient?.let {
                            it.publish(topic, mes)
                        }
                    } catch (e: Exception) {
                        Toast.makeText(activity!!, "không thể kết nối tới topic", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

}