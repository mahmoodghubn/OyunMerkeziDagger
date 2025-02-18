package com.example.oyunmerkezi.ui.main

import android.content.Context
import android.content.DialogInterface
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.oyunmerkezi.R
import com.example.oyunmerkezi.databinding.GameRowBinding
import com.example.oyunmerkezi.util.Game
import java.util.Locale

class GameAdapter(
    private val clickListener: GameListener,
    private val gamesViewModel: GameViewModel,
    private val view: View
) :
    ListAdapter<Game, GameAdapter.ViewHolder>(GameDiffCallback()), Filterable {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(clickListener, getItem(position)!!, gamesViewModel, view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: GameRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            clickListener: GameListener,
            item: Game,
            gamesViewModel: GameViewModel,
            view: View
        ) {


            binding.game = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = GameRowBinding
                    .inflate(layoutInflater, parent, false)

                return ViewHolder(view)
            }
        }
    }
    class GameDiffCallback :
        DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.gameId == newItem.gameId
        }

        override fun getChangePayload(oldItem: Game, newItem: Game): Any? {
            return newItem
        }

        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem == newItem
        }
    }

    private var filterObject: Filter = object : Filter() {
        override fun performFiltering(charSequence: CharSequence): FilterResults {

            return TODO("Provide the return value")
        }

        override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
           //TODO this@GameAdapter.submitList(filterResults.values as List<Game>)
        }
    }

    override fun getFilter(): Filter {
        return filterObject
    }
}

class GameListener(val clickListener: (game: Game) -> Unit) {
    fun onClick(game: Game) = clickListener(game)
}

