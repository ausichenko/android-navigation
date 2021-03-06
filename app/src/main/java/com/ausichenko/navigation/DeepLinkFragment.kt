package com.ausichenko.navigation

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.NotificationCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.Navigation

class DeepLinkFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.deeplink_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tv = view.findViewById<TextView>(R.id.text)
        tv.text = arguments!!.getString("myarg")

        val b = view.findViewById<Button>(R.id.send_notification)
        b.setOnClickListener { v ->
            val editArgs = view.findViewById<EditText>(R.id.edit_args)
            val args = Bundle()
            args.putString("myarg", editArgs.text.toString())
            val deeplink = Navigation.findNavController(v).createDeepLink()
                    .setDestination(R.id.android)
                    .setArguments(args)
                    .createPendingIntent()
            val notificationManager = context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(NotificationChannel(
                        "deeplink", "Deep Links", NotificationManager.IMPORTANCE_HIGH))
            }
            val builder = NotificationCompat.Builder(
                    context!!, "deeplink")
                    .setContentTitle("Navigation")
                    .setContentText("Deep link to Android")
                    .setSmallIcon(R.drawable.ic_android)
                    .setContentIntent(deeplink)
                    .setAutoCancel(true)
            notificationManager.notify(0, builder.build())
        }
    }
}