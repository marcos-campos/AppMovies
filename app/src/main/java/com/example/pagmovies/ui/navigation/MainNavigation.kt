package com.example.pagmovies.ui.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pagmovies.R
import com.example.pagmovies.ui.main.MainFragment
import com.example.pagmovies.ui.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainNavigation: Fragment() {
    val bottomNavigation by lazy { view?.findViewById<BottomNavigationView>(R.id.bottomNavigationView) }

    companion object {
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.menu_fragment, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setCurrentFragment(MainFragment())

        bottomNavigation?.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.movie -> setCurrentFragment(MainFragment())
                R.id.user -> setCurrentFragment(ProfileFragment())
            }
            true
        }
    }
    fun setCurrentFragment(fragment: Fragment)=
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.flFragment,fragment)
                commit()
            }

}