package com.app.newsapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.app.newsapp.R
import com.app.newsapp.adapter.LatestNewsAdapter
import com.app.newsapp.databinding.FragmentHomeBinding
import com.app.newsapp.model.Article
import com.app.newsapp.model.LatestNews
import com.app.newsapp.viewmodel.LatestNewsViewModel


class HomeFragment : Fragment() {

    private var articles: List<Article> = arrayListOf()
    private lateinit var latestNewsAdapter: LatestNewsAdapter
    private lateinit var fragmentHomeBinding: FragmentHomeBinding
    private lateinit var latestNewsViewModel: LatestNewsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        latestNewsViewModel = ViewModelProvider(this)[LatestNewsViewModel::class.java]
        latestNewsViewModel.getData()
        observeLatestNews()

        return fragmentHomeBinding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeLatestNews() {
        val observer = Observer<LatestNews?> { news ->
            if (news != null) {
                articles = news.articles
                latestNewsAdapter = LatestNewsAdapter(articles)
                fragmentHomeBinding.latestNewsRecycler.adapter = latestNewsAdapter
            }
        }
        latestNewsViewModel.latestNews.observe(viewLifecycleOwner, observer)
    }

}