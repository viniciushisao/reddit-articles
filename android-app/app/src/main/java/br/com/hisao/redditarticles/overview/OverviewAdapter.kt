package br.com.hisao.redditarticles.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.hisao.redditarticles.databinding.ListArticleItemBinding
import br.com.hisao.redditarticles.model.json.Children

class OverviewAdapter(val clickListener: ArticleOnClickListener) :
    ListAdapter<Children, ViewHolder>(ChildrenDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}

class ViewHolder private constructor(private val dataBinding: ListArticleItemBinding) :
    RecyclerView.ViewHolder(dataBinding.root) {

    fun bind(item: Children, clickListener: ArticleOnClickListener) {

        dataBinding.articleTitle.text = item.data.title
        dataBinding.articleSelfText.text = item.data.selftext
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

class ChildrenDiffCallback : DiffUtil.ItemCallback<Children>() {
    override fun areItemsTheSame(oldItem: Children, newItem: Children): Boolean {
        return oldItem.data.id == newItem.data.id
    }

    override fun areContentsTheSame(oldItem: Children, newItem: Children): Boolean {
        return oldItem == newItem
    }
}

class ArticleOnClickListener(val clickListener: (articleId: String) -> Unit) {
    fun onClick(children: Children) = clickListener(children.data.id)
}