package com.app.newsapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.app.newsapp.R
import com.app.newsapp.databinding.ItemNewsBinding
import com.app.newsapp.fragment.HomeFragmentDirections
import com.app.newsapp.model.Article

class LatestNewsAdapter(private var articles: List<Article>, private val onItemClick: (Article) -> Unit) : RecyclerView.Adapter<LatestNewsAdapter.ItemHolder>() {

    class ItemHolder(var binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemNewsBinding>(inflater, R.layout.item_news, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.binding.article = articles[position]
        holder.binding.newsLinear.setOnClickListener {
            onItemClick(articles[position])
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newArticleList: List<Article>) {
        articles = emptyList()
        articles = newArticleList
        notifyDataSetChanged()
    }


}