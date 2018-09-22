package my.com.toru.critter.ui.main.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.adapter_list.view.*
import my.com.toru.critter.R
import my.com.toru.critter.model.Critter
import my.com.toru.critter.model.CritterDB
import my.com.toru.critter.util.GlideUtil
import java.util.*

class ListAdapter(private var critters: ArrayList<CritterDB>,
                  private val clickCallback:(CritterDB)->Unit): RecyclerView.Adapter<ListVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_list, parent, false)
        return ListVH(view, clickCallback)
    }

    fun addItems(addedList:ArrayList<CritterDB>){
        critters.addAll(addedList)
        notifyDataSetChanged()
    }

    fun addItem(newData:CritterDB){
        critters.add(newData)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = critters.size

    override fun onBindViewHolder(holder: ListVH, position: Int) {
        holder.apply {
//            GlideUtil.loadImage(critters[position].time, imgView)
            itemView.setOnClickListener { clickCallback(critters[position]) }
        }
    }
}

class ListVH(view:View, val cb:(CritterDB)->Unit):RecyclerView.ViewHolder(view){
    val imgView = view.img!!
}