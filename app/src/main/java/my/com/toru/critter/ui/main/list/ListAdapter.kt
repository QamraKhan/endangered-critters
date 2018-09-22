package my.com.toru.critter.ui.main.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import my.com.toru.critter.R

class ListAdapter(private val clickCallback:()->Unit): RecyclerView.Adapter<ListVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_list, parent, false)
        return ListVH(view)
    }

    override fun getItemCount(): Int = 30

    override fun onBindViewHolder(holder: ListVH, position: Int) {
        holder.apply {
            bindItem()
            itemView.setOnClickListener {
                clickCallback()
            }
        }
    }
}

class ListVH(view:View):RecyclerView.ViewHolder(view){
    fun bindItem(){}
}