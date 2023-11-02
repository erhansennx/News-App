package com.app.newsapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.app.newsapp.R
import com.app.newsapp.adapter.LatestNewsAdapter
import com.app.newsapp.databinding.FragmentSearchBinding
import com.app.newsapp.model.Article
import com.app.newsapp.model.NewsModel
import com.app.newsapp.viewmodel.SearchNewsViewModel


class SearchFragment : Fragment() {

    private var articles: List<Article> = arrayListOf()
    private var filteredList: List<Article> = arrayListOf()
    private lateinit var latestNewsAdapter: LatestNewsAdapter
    private lateinit var searchNewsViewModel: SearchNewsViewModel
    private lateinit var fragmentSearchBinding: FragmentSearchBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        searchNewsViewModel = ViewModelProvider(this)[SearchNewsViewModel::class.java]
        searchNewsViewModel.getAllNews()
        observeAllNews()

        return fragmentSearchBinding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeAllNews() {
        val observer = Observer<NewsModel?> { news ->
            if (news != null) {
                fragmentSearchBinding.searchProgress.visibility = View.GONE
                fragmentSearchBinding.searchLinear.visibility = View.VISIBLE
                articles = news.articles.filter { it.urlToImage != null }
                latestNewsAdapter = LatestNewsAdapter(articles, onItemClick = { article ->
                    val action = SearchFragmentDirections.actionSearchFragmentToDetailsFragment(article, "SearchFragment")
                    Navigation.findNavController(requireView()).navigate(action)
                })
                fragmentSearchBinding.searchRecycler.adapter = latestNewsAdapter
                searchNews()
            }
        }
        searchNewsViewModel.newsModel.observe(viewLifecycleOwner, observer)
    }


    private fun searchNews() {
        fragmentSearchBinding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            View.OnFocusChangeListener {
            override fun onQueryTextSubmit(p0: String?): Boolean { return false }

            override fun onQueryTextChange(p0: String?): Boolean {

                filteredList = articles.filter { it.title!!.contains(p0!!, ignoreCase = true) }
                latestNewsAdapter.updateList(filteredList)

                if (filteredList.isNotEmpty()) fragmentSearchBinding.noResultView.visibility = View.GONE
                else fragmentSearchBinding.noResultView.visibility = View.VISIBLE

                return false
            }

            override fun onFocusChange(p0: View?, p1: Boolean) { }
        })
    }



}