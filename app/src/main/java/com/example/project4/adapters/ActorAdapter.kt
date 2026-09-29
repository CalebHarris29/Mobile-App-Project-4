package com.example.project4.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project4.R
import com.example.project4.databinding.ItemActorBinding
import com.example.project4.models.Actor

class ActorAdapter(
    private val actors: List<Actor>,
    private val onActorClick: (Actor) -> Unit
) : RecyclerView.Adapter<ActorAdapter.ActorViewHolder>() {

    inner class ActorViewHolder(val binding: ItemActorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(actor: Actor) {
            binding.tvActorName.text = actor.name
            binding.tvKnownDepartment.text = "Department: ${actor.knownForDepartment ?: "N/A"}"
            binding.tvTopWork.text = "Top Work: ${actor.topWorkTitle}"

            if (actor.fullImageUrl.isNotEmpty()) {
                Glide.with(itemView.context)
                    .load(actor.fullImageUrl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(binding.ivActorProfile)
            } else {
                binding.ivActorProfile.setImageResource(R.drawable.ic_launcher_background)
            }

            itemView.setOnClickListener {
                onActorClick(actor)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val binding = ItemActorBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ActorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(actors[position])
    }

    override fun getItemCount(): Int = actors.size
}
