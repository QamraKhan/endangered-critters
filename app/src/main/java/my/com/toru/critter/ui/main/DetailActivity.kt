package my.com.toru.critter.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import com.squareup.picasso.Picasso
import my.com.toru.critter.R

class DetailActivity : AppCompatActivity() {
    private lateinit var imageUrl:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        imageUrl = intent.getStringExtra("IMAGE_URL")
        if(imageUrl.contains(".jpg")){
            imageUrl = imageUrl.replace("\\","")
            imageUrl = "http://$imageUrl"
            val imageView = findViewById<ImageView>(R.id.detail_img)
            Picasso.get()
                    .load(imageUrl)
                    .fit()
                    .into(imageView)
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
