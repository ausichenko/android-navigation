package com.ausichenko.navigation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*

class ShoppingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shopping, container, false)
    }
}