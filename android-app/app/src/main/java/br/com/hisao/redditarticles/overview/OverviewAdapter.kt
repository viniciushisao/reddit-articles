package br.com.hisao.redditarticles.overview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.hisao.redditarticles.databinding.ListArticleItemBinding
import br.com.hisao.redditarticles.model.json.DataX
import com.bumptech.glide.Glide

class OverviewAdapter(private val clickListener: ArticleOnClickListener) :
    ListAdapter<DataX, ViewHolder>(DataXDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}

class ViewHolder private constructor(private val dataBinding: ListArticleItemBinding) :
    RecyclerView.ViewHolder(dataBinding.root) {

    fun bind(item: DataX, clickListener: ArticleOnClickListener) {

        dataBinding.articleTitle.text = item.title
        dataBinding.articleSelfText.text = item.selftext
        if (item.thumbnail == null || item.thumbnail!!.isEmpty()) {
            dataBinding.thumb.visibility = View.GONE
        } else {
            Log.d("REDDIT_ARTICLES", "ViewHolder:bind: HAS IMAGE: " + item.thumbnail)
            dataBinding.thumb.visibility = View.VISIBLE
            Glide
                .with(this.itemView.context)
                .load(item.thumbnail)
                .centerCrop()
                .placeholder(android.R.drawable.spinner_background)
                .into(dataBinding.thumb);
        }
        dataBinding.root.setOnClickListener {
            clickListener.onClick(item)
        }
    }

    companion object {
        fun from(parent: ViewGroup): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val viewBinding = ListArticleItemBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(viewBinding)
        }
    }
}

class DataXDiffCallback : DiffUtil.ItemCallback<DataX>() {
    override fun areItemsTheSame(oldItem: DataX, newItem: DataX): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataX, newItem: DataX): Boolean {
        return oldItem == newItem
    }
}

class ArticleOnClickListener(val clickListener: (articleId: String) -> Unit) {
    fun onClick(dataX: DataX) = clickListener(dataX.id)
}