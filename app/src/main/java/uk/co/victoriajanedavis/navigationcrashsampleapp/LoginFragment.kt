package uk.co.victoriajanedavis.navigationcrashsampleapp

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usernameEditText.afterTextChanged { toggleLoginButton() }
        passwordEditText.afterTextChanged { toggleLoginButton() }

        loginButton.setOnClickListener {
            hideKeyboard()
            sharedPreferences
                .edit()
                .putBoolean("isLoggedIn", true)
                .apply()
            findNavController().navigate(R.id.action_loginFragment_to_chatFlowGraph)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
    }

    private fun toggleLoginButton() {
        loginButton.isEnabled = usernameEditText.isNotEmpty() && passwordEditText.isNotEmpty()
    }
}