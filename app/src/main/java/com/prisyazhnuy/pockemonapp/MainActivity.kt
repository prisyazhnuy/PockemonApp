package com.prisyazhnuy.pockemonapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.prisyazhnuy.pockemonapp.pockemon_details.PockemonDetailsFragment
import com.prisyazhnuy.pockemonapp.pockemon_list.PockemonCallback
import com.prisyazhnuy.pockemonapp.pockemon_list.PockemonListFragment
import org.jetbrains.anko.dip
import org.jetbrains.anko.padding
import org.jetbrains.anko.verticalLayout

class MainActivity : AppCompatActivity(),
        PockemonCallback {

    companion object {
        const val CONTAINER_ID = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
            id = CONTAINER_ID
            padding = dip(30)
        }
        replaceFragment(PockemonListFragment.newInstance())
    }

    override fun showPockemonDetails(name: String) {
        replaceFragment(PockemonDetailsFragment.newInstance(name))
    }

    protected fun replaceFragment(fragment: Fragment, needToAddToBackStack: Boolean = true) {
        val name = fragment.javaClass.simpleName
        supportFragmentManager.beginTransaction().apply {
            replace(CONTAINER_ID, fragment, name)
            if (needToAddToBackStack) addToBackStack(name)
        }.commit()
    }
}
