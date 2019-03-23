package home.homeimpl

import android.content.Context
import home.interfaceHome.LoginInterface
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*

class LoginImpl constructor(var view: LoginInterface.View, val context: Context) : LoginInterface.Presenter {

    override fun login(url: String, port: String, user: String, pass: String) {
        val client = MqttAndroidClient(context, "tcp://$url:$port", MqttClient.generateClientId())
        val mqttConnectOptions = MqttConnectOptions().apply {
            isAutomaticReconnect = true
            isCleanSession = false
            userName = user
            password = pass.toCharArray()
        }
        try {
            client.connect(mqttConnectOptions, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    val disconnectedBufferOptions = DisconnectedBufferOptions().apply {
                        isBufferEnabled = true
                        bufferSize = 100
                        isPersistBuffer = false
                        isDeleteOldestMessages = false
                    }
                    client.setBufferOpts(disconnectedBufferOptions)
                    view.onSuccess(asyncActionToken,client)

                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    view.onFailure()
                }
            })

        } catch (e: MqttException) {
            view.onFailure()
        }
    }

}