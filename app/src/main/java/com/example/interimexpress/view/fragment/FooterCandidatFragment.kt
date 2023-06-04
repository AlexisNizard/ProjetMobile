package com.example.interimexpress.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.interimexpress.R

class FooterCandidatFragment : Fragment() {

    private lateinit var notificationIcon: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_footer_candidat, container, false)
        notificationIcon = view.findViewById(R.id.notification_icon)
        return view
    }

    fun setNotificationIconActive(active: Boolean) {
        val drawableId = if (active) {
            R.drawable.baseline_notifications_active_24
        } else {
            R.drawable.baseline_notifications_24
        }
        notificationIcon.setImageResource(drawableId)
    }
}
