package home.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import chinh.pham.mqtt.R
import home.HomeActivity
import home.homeimpl.LoginImpl
import home.interfaceHome.LoginInterface
import kotlinx.android.synthetic.main.fragment_login.*
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.IMqttToken
import utils.FragmentUtil

class FragmentLogin : Fragment(), LoginInterface.View {


    override fun onSuccess(asyncActionToken: IMqttToken?, client: MqttAndroidClient) {

        activity?.let {
            val fragment = FragmentReceiveData()
            fragment.mqttClient = client
            (it as HomeActivity).loadFragment(fragment,FragmentUtil.fragmentReceive)
        }
    }

    override fun onFailure() {
        Toast.makeText(activity!!, "onFailure", Toast.LENGTH_LONG).show()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            (it as HomeActivity).lockDrawer()
        }

        val presenter = LoginImpl(this@FragmentLogin, activity!!)
        cardView.setOnClickListener {
            if (!edtURL.text.isNullOrBlank() && !edtPort.text.isNullOrBlank()
                    && !edtPass.text.isNullOrBlank()
                    && !edtUser.text.isNullOrBlank()) {
                presenter.login(edtURL.text.toString(), edtPort.text.toString(),
                        edtUser.text.toString(), edtPass.text.toString())
            }
        }
    }
}