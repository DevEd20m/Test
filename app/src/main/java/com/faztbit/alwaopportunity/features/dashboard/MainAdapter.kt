package com.faztbit.alwaopportunity.features.dashboard

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.faztbit.alwaopportunity.R
import com.faztbit.alwaopportunity.databinding.ItemMachineBinding
import com.faztbit.alwaopportunity.domain.models.MachineDomain
import com.faztbit.alwaopportunity.features.utils.basicDiffUtil
import com.faztbit.alwaopportunity.features.utils.inflate

class MainAdapter() :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var list: List<MachineDomain> by basicDiffUtil(
        emptyList(), { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ViewHolder(parent.inflate(R.layout.item_machine, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal fun bind(item: MachineDomain) {
            ItemMachineBinding.bind(itemView).run {
                textViewDescription.text = item.name
                textViewPriority.text = item.name
            }
        }
    }
}