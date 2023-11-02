package com.app.newsapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.newsapp.R
import com.app.newsapp.databinding.FragmentDetailsBinding
import com.app.newsapp.model.Article
import com.app.newsapp.viewmodel.NewsDetailViewModel

class DetailsFragment : Fragment() {

    private var isBookmarked = false
    private var source: String? = null
    private lateinit var selectedArticle: Article
    private val newsDetailViewModel : NewsDetailViewModel by viewModels()
    private lateinit var fragmentDetailsBinding: FragmentDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        arguments?.let {
            selectedArticle = DetailsFragmentArgs.fromBundle(it).selectedArticle
            source = DetailsFragmentArgs.fromBundle(it).source
            fragmentDetailsBinding.selectedNews = selectedArticle
        }

        if (source == "ArchiveFragment") {
            isBookmarked = true
            fragmentDetailsBinding.saveButton.background = ContextCompat.getDrawable(requireContext(), R.drawable.baseline_bookmark_added_24)
        }

        return fragmentDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentDetailsBinding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        fragmentDetailsBinding.shareButton.setOnClickListener {
            newsDetailViewModel.shareTheNewsUrl(requireContext(), selectedArticle.url!!)
        }

        fragmentDetailsBinding.saveButton.setOnClickListener {
            if (isBookmarked) {
                it.background = ContextCompat.getDrawable(requireContext(), R.drawable.baseline_bookmark_border_24)
                newsDetailViewModel.deleteFromDatabase(selectedArticle)
                isBookmarked = false
            } else {
                it.background = ContextCompat.getDrawable(requireContext(), R.drawable.baseline_bookmark_added_24)
                newsDetailViewModel.saveInDatabase(selectedArticle)
                isBookmarked = true
            }
        }


    }




}