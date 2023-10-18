package com.app.newsapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.newsapp.R
import com.app.newsapp.adapter.LatestNewsAdapter
import com.app.newsapp.databinding.FragmentHomeBinding
import com.app.newsapp.model.Article
import com.app.newsapp.model.LatestNews
import com.app.newsapp.viewmodel.LatestNewsViewModel
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel


class HomeFragment : Fragment() {

    private var articles: List<Article> = arrayListOf()
    private var imageList: ArrayList<SlideModel> = arrayListOf()
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
                fragmentHomeBinding.homeProgress.visibility = View.GONE
                fragmentHomeBinding.homeLinear.visibility = View.VISIBLE
                articles = news.articles.filter { it.urlToImage != null }
                addImageSlider(articles)
                latestNewsAdapter = LatestNewsAdapter(articles)
                fragmentHomeBinding.latestNewsRecycler.adapter = latestNewsAdapter
            }
        }
        latestNewsViewModel.latestNews.observe(viewLifecycleOwner, observer)
    }

    private fun addImageSlider(articles: List<Article>) {
        imageList.clear()
        for (index in 0 until 7) {
            imageList.add(SlideModel(articles[index].urlToImage, articles[index].title))
        }
        fragmentHomeBinding.imageSlider.setImageList(imageList, ScaleTypes.FIT)
    }


}