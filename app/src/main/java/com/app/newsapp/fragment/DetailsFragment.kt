package com.app.newsapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.app.newsapp.R
import com.app.newsapp.databinding.FragmentDetailsBinding
import com.app.newsapp.model.Article

class DetailsFragment : Fragment() {

    private lateinit var selectedArticle: Article
    private lateinit var fragmentDetailsBinding: FragmentDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        arguments?.let {
            selectedArticle = DetailsFragmentArgs.fromBundle(it).selectedArticle
            fragmentDetailsBinding.selectedNews = selectedArticle
        }

        return fragmentDetailsBinding.root
    }

}