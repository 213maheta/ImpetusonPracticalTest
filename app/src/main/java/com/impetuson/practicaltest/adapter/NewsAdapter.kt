package com.impetuson.practicaltest.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.impetuson.practicaltest.R
import com.impetuson.practicaltest.databinding.ItemRvNewsBinding
import com.impetuson.practicaltest.model.ResponseNews
import javax.inject.Inject

class NewsAdapter @Inject constructor(): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    val newsList = arrayListOf<ResponseNews>()

    class ViewHolder(val binding:ItemRvNewsBinding):RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding:ItemRvNewsBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_rv_news,parent,false);
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = newsList[position]
        Glide.with(holder.itemView.context).load(news.imageUrl).into(holder.binding.ivNews)
        holder.binding.tvTitle.text = news.title
        holder.binding.tvDescription.text = news.summary
    }
}
