package com.app.newsapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.app.newsapp.R
import com.app.newsapp.databinding.FragmentArchiveBinding
import com.app.newsapp.model.Article
import com.app.newsapp.viewmodel.NewsDetailViewModel


class ArchiveFragment : Fragment() {

    private val newsDetailViewModel: NewsDetailViewModel by viewModels()
    private lateinit var fragmentArchiveBinding: FragmentArchiveBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentArchiveBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_archive, container, false)

        newsDetailViewModel.getSavedNews()
        observeArchive()

        return fragmentArchiveBinding.root
    }


    private fun observeArchive() {
        val observerArticle = Observer<List<Article>> { articles ->
            if (articles.isNotEmpty()) {
                for (i in articles) {
                    println("Data: ${i.title}")
                }
            }
        }
        newsDetailViewModel.articleLiveData.observe(viewLifecycleOwner, observerArticle)
    }


}