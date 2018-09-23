package my.com.toru.critter.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import my.com.toru.critter.R
import my.com.toru.critter.model.CritterNewDB

class DetailActivity : AppCompatActivity() {
    private lateinit var newDB:CritterNewDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        newDB = intent.getSerializableExtra("CRITTER") as CritterNewDB
        if(newDB.imageUrl.contains(".jpg")){
            var imageUrl = newDB.imageUrl.replace("\\","")
            imageUrl = "http://$imageUrl"
            val imageView = findViewById<ImageView>(R.id.detail_img)
            Picasso.get()
                    .load(imageUrl)
                    .fit()
                    .into(imageView)
        }
        newDB.apply {
            classify_des_txt.text = classification
            humidity_des_txt.text = humidity
            temperature_des_txt.text = temperature

            when(classification){
                "orang_utan"->{
                    species_des_txt.text = resources.getString(R.string.desc_orang)
                    conservation_status_txt.text = resources.getString(R.string.conservation_orang)
                    number_txt.text = resources.getString(R.string.numbers_orang)
                }
                "chimpanzee"->{
                    species_des_txt.text = resources.getString(R.string.desc_chimpanzee)
                    conservation_status_txt.text = resources.getString(R.string.conservation_chimpanzee)
                    number_txt.text = resources.getString(R.string.numbers_chimpanzee)
                }

                "malayan_tiger"->{
                    species_des_txt.text = resources.getString(R.string.desc_malayan_tiger)
                    conservation_status_txt.text = resources.getString(R.string.conservation_malayan_tiger)
                    number_txt.text = resources.getString(R.string.numbers_malayan_tiger)
                }

                "elephant"->{
                    species_des_txt.text = resources.getString(R.string.desc_elephant)
                    conservation_status_txt.text = resources.getString(R.string.conservation_elephant)
                    number_txt.text = resources.getString(R.string.numbers_elephant)
                }

                "lion"->{
                    species_des_txt.text = resources.getString(R.string.desc_lion)
                    conservation_status_txt.text = resources.getString(R.string.conservation_lion)
                    number_txt.text = resources.getString(R.string.numbers_lion)
                }

                "giant_panda"->{
                    species_des_txt.text = resources.getString(R.string.desc_panda)
                    conservation_status_txt.text = resources.getString(R.string.conservation_panda)
                    number_txt.text = resources.getString(R.string.numbers_panda)
                }

                else->{
                    species_des_txt.text = resources.getString(R.string.desc_default)
                    conservation_status_txt.text = resources.getString(R.string.conservation_default)
                    number_txt.text = resources.getString(R.string.numbers_default)
                }
            }
            where_txt.text = "CritterWatchDevice_001, Sabah, Malaysia"
        }

    }

//    private fun initStorage(){
//        val storageReference:StorageReference = FirebaseStorage.getInstance().getReference("photos")
//        storageReference.child("$date-$time.jpg")
//
//        val imgView = findViewById<ImageView>(R.id.detail_img)
//        Glide.with(this@DetailActivity /* context */)
//                .load(storageReference)
//                .into(imgView)
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.menu_settings->{
                startActivity(Intent(this@DetailActivity, MapsActivity::class.java))
                true
            }
            else->{true}
        }
    }
}
