package my.com.toru.critter.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    private var isMap = false

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.menu_settings ->{
                if(!isMap){
                    isMap = true
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.host, ExtendedMapFragment())
                            .commitAllowingStateLoss()
                }
                else{
                    isMap = false
                    val fragment = ItemFragment.newInstance()
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.host, fragment)
                            .commitAllowingStateLoss()
                }
                true
            }
            else->{
                true
            }
        }
    }
}