package br.com.hisao.redditarticles.overview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.hisao.redditarticles.databinding.ListArticleItemBinding
import br.com.hisao.redditarticles.model.json.Article
import br.com.hisao.redditarticles.util.isValidThumbnailUrl
import com.bumptech.glide.Glide

class OverviewAdapter(private val clickListener: ArticleOnClickListener) :
    ListAdapter<Article, ViewHolder>(ArticleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}



class ViewHolder private constructor(private val dataBinding: ListArticleItemBinding) :
    RecyclerView.ViewHolder(dataBinding.root) {

    fun bind(item: Article, clickListener: ArticleOnClickListener) {

        dataBinding.articleTitle.text = item.title
        dataBinding.articleSelfText.text = item.selftext
        if (item.thumbnail.isValidThumbnailUrl()) {
            dataBinding.thumb.visibility = View.GONE
        } else {
            dataBinding.thumb.visibility = View.VISIBLE
            Glide
                .with(this.itemView.context)
                .load(item.thumbnail)
                .centerCrop()
                .placeholder(android.R.drawable.spinner_background)
                .into(dataBinding.thumb)
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

class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}

class ArticleOnClickListener(val clickListener: (articleId: String) -> Unit) {
    fun onClick(article: Article) = clickListener(article.id)
}