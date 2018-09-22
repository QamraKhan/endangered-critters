package my.com.toru.critter.ui.main.list

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_item.*
import my.com.toru.critter.R
import my.com.toru.critter.model.Critter
import my.com.toru.critter.model.CritterDB
import my.com.toru.critter.model.CritterDBModel
import my.com.toru.critter.ui.main.DetailActivity

private const val URL = "https://crittercam-baa64.firebaseio.com/"

class ItemFragment : Fragment() {

    private lateinit var listAdapter:ListAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listAdapter = ListAdapter(ArrayList()) {
            Log.i("ItemFragment", "i:: " + it.date + ", ${it.time}")
            activity?.startActivity(Intent(activity, DetailActivity::class.java))
        }
        item_rcv.apply {
            layoutManager = GridLayoutManager(view.context, 3)
            adapter = listAdapter
        }

        val database = FirebaseDatabase.getInstance()
        val myRef = database.reference
        myRef.child("testdate").addChildEventListener(object:ChildEventListener{
            override fun onCancelled(p0: DatabaseError) {}

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {}

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {}

            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                val eachModel = dataSnapshot.getValue(CritterDB::class.java)
                Log.w("LOG", "date: " + eachModel?.date)
                Log.w("LOG", "time: " + eachModel?.time)

                eachModel?.time = "https://firebasestorage.googleapis.com/v0/b/crittercam-baa64.appspot.com/o/photos%2F2018.09.23-010052.jpg?alt=media&token=968ea1a7-7ed9-4332-876c-52340a40fbb9"

                eachModel?.let {
                    listAdapter.addItem(it)
                }
            }

            override fun onChildRemoved(p0: DataSnapshot) {}
        })

//        // mockup code
//        val newList = ArrayList<Critter>()
//        for(i in 0 until 30){
//            newList.add(Critter(i.toString(),"https://image.11st.my/browsing/banner/2018/07/09/1001/2018070917300949537_7907289_2.png"))
//        }
//        // mockup code end
//        listAdapter.addItem(newList)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ItemFragment()
    }
}