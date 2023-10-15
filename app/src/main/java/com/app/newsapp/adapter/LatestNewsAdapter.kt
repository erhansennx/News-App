package com.app.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.newsapp.R
import com.app.newsapp.databinding.ItemNewsBinding
import com.app.newsapp.model.Article

class LatestNewsAdapter(private val articles: List<Article>) : RecyclerView.Adapter<LatestNewsAdapter.ItemHolder>() {

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
    }


}