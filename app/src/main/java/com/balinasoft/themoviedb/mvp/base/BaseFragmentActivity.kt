package com.balinasoft.themoviedb.mvp.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.balinasoft.themoviedb.R

abstract class BaseFragmentActivity() : MvpAppCompatActivity() {

    abstract fun getFragment(): Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_container)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.ftc_container, getFragment())
                .commit()
        }
    }

    fun getCurrentFragment(): Fragment {
        return supportFragmentManager.findFragmentById(R.id.ftc_container)!!
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.ftc_container)
        if (fragment != null
            && fragment is BackButtonListener
            && (fragment as BackButtonListener).onBackPressed()
        ) {
            return
        } else {
            super.onBackPressed()
            return
        }
    }

}
