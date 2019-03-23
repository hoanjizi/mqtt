package home.interfaceHome

import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.IMqttToken

interface LoginInterface {
    interface Presenter {
        fun login(url: String, port: String, user: String, pass: String)
    }
    interface View {
        fun onSuccess(asyncActionToken: IMqttToken?,client: MqttAndroidClient)
        fun onFailure()
    }
}