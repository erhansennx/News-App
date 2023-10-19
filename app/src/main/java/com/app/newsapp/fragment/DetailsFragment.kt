package com.app.newsapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentDetailsBinding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        fragmentDetailsBinding.shareButton.setOnClickListener {
            shareTheNews(selectedArticle.url!!)
        }

    }

    fun shareTheNews(url: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, url)
        startActivity(Intent.createChooser(intent, "Paylaş"))
    }


}