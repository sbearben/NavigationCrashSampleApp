package uk.co.victoriajanedavis.navigationcrashsampleapp

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_friends.*
import kotlinx.android.synthetic.main.toolbar.*

class FriendsFragment : Fragment(){

    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_friends, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logoutButton.setOnClickListener {
            sharedPreferences
                .edit()
                .putBoolean("isLoggedIn", false)
                .apply()
            findNavController().navigate(R.id.action_friendsFragment_to_loginFlowGraph)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)

        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        if(!isLoggedIn) {
            findNavController().navigate(R.id.action_friendsFragment_to_loginFlowGraph)
        }

        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        getSupportActionBar()?.apply {
            setDisplayShowTitleEnabled(true)
            title = "Friends"
        }
    }
}