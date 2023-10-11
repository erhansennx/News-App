package com.app.newsapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.newsapp.R
import com.app.newsapp.databinding.FragmentHomeBinding
import com.app.newsapp.model.LatestNews
import com.app.newsapp.viewmodel.LatestNewsViewModel


class HomeFragment : Fragment() {

    private lateinit var fragmentHomeBinding: FragmentHomeBinding
    private lateinit var latestNewsViewModel: LatestNewsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

        latestNewsViewModel = ViewModelProvider(this)[LatestNewsViewModel::class.java]
        latestNewsViewModel.getData()

        observeLatestNews()

        return fragmentHomeBinding.root
    }

    private fun observeLatestNews() {
        val observer = Observer<LatestNews?> { news ->
            if (news != null) {
                // data
            }
        }
        latestNewsViewModel.latestNews.observe(viewLifecycleOwner, observer)

    }

}