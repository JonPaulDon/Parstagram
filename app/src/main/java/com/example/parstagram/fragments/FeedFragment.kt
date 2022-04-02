package com.example.parstagram.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.parstagram.MainActivity
import com.example.parstagram.Post
import com.example.parstagram.PostAdapter
import com.example.parstagram.R
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

open class FeedFragment : Fragment() {
    lateinit var postsRecyclerView: RecyclerView
    lateinit var adapter : PostAdapter
    lateinit var swipeRefreshLayout : SwipeRefreshLayout
    var allPosts: MutableList<Post> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //This is where we set up our views
        postsRecyclerView=view.findViewById(R.id.postRecyclerView)
        swipeRefreshLayout=view.findViewById<SwipeRefreshLayout?>(R.id.swipeContainer)
            swipeRefreshLayout.setOnRefreshListener {
            //queryPosts()

        }
        //Recyclerview steps
        //Create layout for each row in list

        //Create data source for each row
        //Create adapter that will bridge data and row layout
        //Set adapter on rcView
        adapter= PostAdapter(requireContext(),allPosts)
        postsRecyclerView.adapter= adapter
        //Set layout manager on rcView
        postsRecyclerView.layoutManager= LinearLayoutManager(requireContext())

        queryPosts()
    }
    open fun queryPosts(){
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
       // allPosts.clear()
        //Find all post objects
        query.include(Post.KEY_USER)
        query.addDescendingOrder("createdAt")
        query.setLimit(20)
        //TODO Only return the most recent 20 posts
        query.findInBackground(object : FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?) {
                if(e!=null){
                    Log.e(TAG,"Error fetching posts")
                }else {
                    if(posts!=null){
                        for(post in posts){
                            Log.i(TAG,"Post: "+post.getDescription()+", username :"+ post.getUser()?.username)

                        }
                        allPosts.addAll(posts)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        })

    }

companion object{
    const val TAG="FeedFragment"
}

}