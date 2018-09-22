package my.com.toru.critter.ui.main.list

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_item.*

import my.com.toru.critter.R
import my.com.toru.critter.model.Critter
import my.com.toru.critter.ui.main.DetailActivity


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
            Log.i("ItemFragment", "i:: " + it.str)
            activity?.startActivity(Intent(activity, DetailActivity::class.java))
        }
        item_rcv.apply {
            layoutManager = GridLayoutManager(view.context, 3)
            adapter = listAdapter
        }

        // mockup code
        val newList = ArrayList<Critter>()
        for(i in 0 until 30){
            newList.add(Critter(i.toString(),"https://image.11st.my/browsing/banner/2018/07/09/1001/2018070917300949537_7907289_2.png"))
        }
        // mockup code end
        listAdapter.addItem(newList)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ItemFragment()
    }
}
