package my.com.toru.critter.ui.main.list

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_item.*
import my.com.toru.critter.R
import my.com.toru.critter.remote.ApiHelper
import my.com.toru.critter.ui.main.DetailActivity

private const val URL = "https://crittercam-baa64.firebaseio.com/"

class ItemFragment : Fragment() {

    private lateinit var listAdapter:ListAdapter
    private lateinit var progressBar:ProgressBar
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_item, container, false)
        progressBar = view.findViewById<ProgressBar>(R.id.loading_progress)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Inflate the layout for this fragment
        fab_refresh.setOnClickListener { _ ->
            progressBar.visibility = View.VISIBLE
            ApiHelper.getData({
                progressBar.visibility = View.GONE
                listAdapter.addItems2(it)

            }, {
                progressBar.visibility = View.GONE
                activity?.let {
                    Toast.makeText(it, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
            })
        }

        listAdapter = ListAdapter(ArrayList()) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra("CRITTER", it)
            activity?.startActivity(intent)
        }
        item_rcv.apply {
            layoutManager = GridLayoutManager(activity, 3)
            adapter = listAdapter
        }


        progressBar.visibility = View.VISIBLE
        ApiHelper.getData({
            progressBar.visibility = View.GONE
            listAdapter.addItems2(it)

        }, {
            progressBar.visibility = View.GONE
            activity?.let {
                Toast.makeText(it, "Failed to fetch data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = ItemFragment()
    }
}