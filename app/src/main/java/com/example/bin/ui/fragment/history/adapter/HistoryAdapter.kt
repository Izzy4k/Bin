package com.example.bin.ui.fragment.history.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bin.databinding.ItemHistoryBinding
import com.example.domain.bin.entity.BinHistory

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
     var list = listOf<BinHistory>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder =
        HistoryViewHolder(
            ItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class HistoryViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(binHistory: BinHistory) {
            with(binding) {
                itemTxtBank.text = binHistory.bin
                itemTxtData.text = binHistory.data
                itemTxtTime.text = binHistory.time
            }
        }

    }
}