package com.app.newsapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.app.newsapp.R
import com.app.newsapp.adapter.ArchiveAdapter
import com.app.newsapp.databinding.FragmentArchiveBinding
import com.app.newsapp.model.Article
import com.app.newsapp.viewmodel.NewsDetailViewModel


class ArchiveFragment : Fragment() {

    private lateinit var archiveAdapter: ArchiveAdapter
    private val newsDetailViewModel: NewsDetailViewModel by viewModels()
    private lateinit var fragmentArchiveBinding: FragmentArchiveBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentArchiveBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_archive, container, false)

        newsDetailViewModel.getSavedNews()
        observeArchive()

        return fragmentArchiveBinding.root
    }


    private fun observeArchive() {
        with(fragmentArchiveBinding) {
            val observerArticle = Observer<List<Article>> { articles ->
                if (articles.isNotEmpty()) {
                    archiveAdapter = ArchiveAdapter(articles, onItemClick = {
                        val action = ArchiveFragmentDirections.actionArchiveFragmentToDetailsFragment(it, "ArchiveFragment")
                        Navigation.findNavController(requireView()).navigate(action)
                    })
                    archiveRecycler.adapter = archiveAdapter
                    archiveRecycler.visibility = View.VISIBLE
                    archiveProgress.visibility = View.GONE
                    warningText.visibility = View.GONE
                } else {
                    archiveRecycler.visibility = View.GONE
                    archiveProgress.visibility = View.GONE
                    warningText.visibility = View.VISIBLE
                }
            }
            newsDetailViewModel.articleLiveData.observe(viewLifecycleOwner, observerArticle)
        }
    }


}