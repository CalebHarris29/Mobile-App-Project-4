package com.example.project4

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.project4.databinding.ActivityDetailBinding
import com.example.project4.models.Actor

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Actor Details"

        val actor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(EXTRA_ACTOR, Actor::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra(EXTRA_ACTOR) as? Actor
        }

        if (actor != null) {
            bindActorData(actor)
        }
    }

    private fun bindActorData(actor: Actor) {
        binding.tvDetailName.text = actor.name
        binding.tvDetailDepartment.text = "Department: ${actor.knownForDepartment ?: "N/A"}"

        // New pieces of data not in main view:
        binding.tvDetailPopularity.text = "Popularity Rating: ${actor.popularity ?: 0.0}"
        binding.tvDetailGender.text = "Gender: ${actor.genderString}"
        binding.tvDetailActorId.text = "TMDB Actor ID: ${actor.id}"
        binding.tvDetailKnownFor.text = actor.knownForSummary

        if (actor.fullImageUrl.isNotEmpty()) {
            Glide.with(this)
                .load(actor.fullImageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(binding.ivDetailProfile)
        } else {
            binding.ivDetailProfile.setImageResource(R.drawable.ic_launcher_background)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.color.background_dark || item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_ACTOR = "extra_actor"
    }
}
