package my.com.toru.critter.ui.main.list

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_item.*

import my.com.toru.critter.R
import my.com.toru.critter.model.Critter


class ItemFragment() : Fragment() {

    private lateinit var listAdapter:ListAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listAdapter = ListAdapter(ArrayList()) {
            Toast.makeText(view.context, "Test!!", Toast.LENGTH_SHORT).show()
        }
        item_rcv.apply {
            layoutManager = GridLayoutManager(view.context, 3)
            adapter = listAdapter
        }

        // mockup code
        val newList = ArrayList<Critter>()
        for(i in 0 until 30){
            newList.add(Critter("1","a"))
        }
        // mockup code end
        listAdapter.addItem(newList)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ItemFragment()
    }
}
