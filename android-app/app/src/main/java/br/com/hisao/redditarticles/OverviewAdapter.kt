package br.com.hisao.redditarticles

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.hisao.redditarticles.databinding.ListArticleItemBinding
import br.com.hisao.redditarticles.model.json.Children


class OverviewAdapter : RecyclerView.Adapter<ViewHolder>() {
    var data = listOf<Children>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
}

class ViewHolder private constructor(viewBinding: ListArticleItemBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    var title: TextView = viewBinding.articleTitle
    var desc: TextView = viewBinding.articleSelfText

    fun bind(item: Children) {
        title.text = item.data.title
        desc.text = item.data.selftext
    }

    companion object {
        fun from(parent: ViewGroup): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val viewBinding = ListArticleItemBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(viewBinding)
        }
    }
}
