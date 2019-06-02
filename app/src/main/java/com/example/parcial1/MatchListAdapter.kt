package com.example.parcial1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MatchListAdapter internal constructor(
        context: Context
) : RecyclerView.Adapter<MatchListAdapter.MatchViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var matches = emptyList<Match>() // Cached copy of words

    inner class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val matchItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return MatchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val current = matches[position]
        holder.matchItemView.text = current.match
    }

    internal fun setMatches(matches: List<Match>) {
        this.matches = matches
        notifyDataSetChanged()
    }

    override fun getItemCount() = matches.size
}