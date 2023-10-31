package com.app.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.newsapp.R
import com.app.newsapp.databinding.ItemArchiveBinding
import com.app.newsapp.databinding.ItemNewsBinding
import com.app.newsapp.model.Article

class ArchiveAdapter(private var articles: List<Article>) : RecyclerView.Adapter<ArchiveAdapter.ItemHolder>() {

    class ItemHolder(var binding: ItemArchiveBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemArchiveBinding>(inflater, R.layout.item_archive, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.binding.article = articles[position]
    }


}