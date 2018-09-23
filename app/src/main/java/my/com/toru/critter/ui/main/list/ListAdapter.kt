package my.com.toru.critter.ui.main.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_list.view.*
import my.com.toru.critter.R
import my.com.toru.critter.model.Critter
import my.com.toru.critter.model.CritterDB
import my.com.toru.critter.model.CritterNewDB
import my.com.toru.critter.util.GlideUtil
import java.util.*

class ListAdapter(private var critters: ArrayList<CritterNewDB>,
                  private val clickCallback:(CritterNewDB)->Unit): RecyclerView.Adapter<ListVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_list, parent, false)
        return ListVH(view, clickCallback)
    }

    fun addItems2(addedList:ArrayList<CritterNewDB>){
        if(!critters.isEmpty()){
            critters.clear()
        }
        critters.addAll(addedList)
        notifyDataSetChanged()
    }

//    fun addItems(addedList:ArrayList<CritterDB>){
//        critters.addAll(addedList)
//        notifyDataSetChanged()
//    }
//
//    fun addItem(newData:CritterDB){
//        critters.add(newData)
//        notifyDataSetChanged()
//    }

    override fun getItemCount(): Int = critters.size

    override fun onBindViewHolder(holder: ListVH, position: Int) {
        holder.apply {
//            GlideUtil.loadImage(critters[position].time, imgView)

            if(critters[position].imageUrl.contains(".jpg")){

                var imageUrl = critters[position].imageUrl.replace("\\","")
                imageUrl = "http://$imageUrl"

                Picasso.get()
                        .load(imageUrl)
                        .resize(300, 300)
                        .centerCrop()
                        .into(imgView)
            }

            itemView.setOnClickListener { clickCallback(critters[position]) }
        }
    }
}

class ListVH(view:View, val cb:(CritterNewDB)->Unit):RecyclerView.ViewHolder(view){
    val imgView = view.img!!
}