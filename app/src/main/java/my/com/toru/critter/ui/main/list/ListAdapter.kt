package my.com.toru.critter.ui.main.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import my.com.toru.critter.R
import my.com.toru.critter.model.Critter
import java.util.*

class ListAdapter(private var critters: ArrayList<Critter>,
                  private val clickCallback:(Critter)->Unit): RecyclerView.Adapter<ListVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_list, parent, false)
        return ListVH(view, clickCallback)
    }

    fun addItem(addedList:ArrayList<Critter>){
        critters.addAll(addedList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = critters.size

    override fun onBindViewHolder(holder: ListVH, position: Int) {
        holder.apply {
            bindItem(critters[position])
        }
    }
}

class ListVH(view:View, val cb:(Critter)->Unit):RecyclerView.ViewHolder(view){
    fun bindItem(critter:Critter){
        itemView.setOnClickListener {
            cb(critter)
        }
    }
}