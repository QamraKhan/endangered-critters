package my.com.toru.critter.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import my.com.toru.critter.R
import my.com.toru.critter.ui.main.list.ItemFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = ItemFragment.newInstance()
        supportFragmentManager.beginTransaction()
                .replace(R.id.host, fragment)
                .commitAllowingStateLoss()
    }
}