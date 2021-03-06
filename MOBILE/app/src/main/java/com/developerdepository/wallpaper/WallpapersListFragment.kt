package com.developerdepository.wallpaper

import android.content.ContentValues
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developerdepository.wallpaper.Common.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_wallpapers_list.*

class WallpapersListFragment : Fragment(), (Wallpaper) -> Unit {

    private var navController: NavController? = null

    private var wallpapersList: List<Wallpaper> = ArrayList()
    private val wallpapersListAdapter: WallpapersListAdapter =
        WallpapersListAdapter(wallpapersList, this)

    private var isLoading: Boolean = true

    private val wallpapersViewModel : WallpapersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wallpapers_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        wallpapersViewModel.application = requireActivity().application

//        if (firebaseRepository.getUser() == null) {
//            navController!!.navigate(R.id.action_wallpapersListFragment_to_registerFragment)
//        }
        val user = FirebaseAuth.getInstance().currentUser
        user?.getIdToken(true)?.addOnSuccessListener { result ->
            val idToken = result?.token
            Log.d(ContentValues.TAG, "GetTokenResult result = $idToken")
        }

        list_title!!.setText(Common.wallpaperListTitle)

        back_arrow_btn.setOnClickListener {
            requireActivity().onBackPressed()
        }

        wallpapers_list!!.setHasFixedSize(true)
        wallpapers_list!!.layoutManager = GridLayoutManager(context, 2)
        wallpapers_list!!.adapter = wallpapersListAdapter

        //Reached Bottom of RecyclerView
        wallpapers_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //Reached at bottom and not scrolling anymore
                    if (!isLoading) {
                        wallpapersViewModel.loadWallpapersData()
                        isLoading = true
                    }
                }
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        wallpapersViewModel.getWallpapersList().observe(viewLifecycleOwner, Observer { it ->
//            Log.d("DEBUG5", "ADD MORE")
            wallpapersList = it
            wallpapersListAdapter.wallpapersList = wallpapersList
            wallpapersListAdapter.notifyDataSetChanged()

            //Loading complete
            isLoading = false
        })
    }

    override fun invoke(wallpaper: Wallpaper) {
        //Clicked on wallpaper item in the list
        Log.d("DEBUG7", wallpaper.large.toString())
        val action =
            WallpapersListFragmentDirections.actionWallpapersListFragmentToWallpaperViewFragment(
//                Base64.encodeToString(wallpaper.large, Base64.DEFAULT)
                wallpaper.id
            )
        navController!!.navigate(action)
    }

}