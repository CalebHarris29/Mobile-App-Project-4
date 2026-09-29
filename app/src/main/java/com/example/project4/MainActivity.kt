package com.example.project4

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project4.adapters.ActorAdapter
import com.example.project4.databinding.ActivityMainBinding
import com.example.project4.models.Actor
import com.example.project4.models.PopularPeopleResponse
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val actors = mutableListOf<Actor>()
    private lateinit var adapter: ActorAdapter
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Popular Actors"

        setupRecyclerView()
        fetchPopularActors()
    }

    private fun setupRecyclerView() {
        adapter = ActorAdapter(actors) { actor ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_ACTOR, actor)
            }
            startActivity(intent)
        }
        binding.rvActors.layoutManager = LinearLayoutManager(this)
        binding.rvActors.adapter = adapter
    }

    private fun fetchPopularActors() {
        binding.progressBar.visibility = View.VISIBLE
        val apiKey = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
        val url = "https://api.themoviedb.org/3/person/popular?api_key=$apiKey"

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        this@MainActivity,
                        "Error fetching actors: ${e.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val jsonString = response.body?.string()
                if (response.isSuccessful && !jsonString.isNullOrEmpty()) {
                    try {
                        val peopleResponse = Gson().fromJson(
                            jsonString,
                            PopularPeopleResponse::class.java
                        )
                        val fetchedActors = peopleResponse.results

                        runOnUiThread {
                            binding.progressBar.visibility = View.GONE
                            actors.clear()
                            actors.addAll(fetchedActors)
                            adapter.notifyItemRangeInserted(0, fetchedActors.size)
                        }
                    } catch (e: Exception) {
                        runOnUiThread {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(
                                this@MainActivity,
                                "Error parsing data: ${e.localizedMessage}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                } else {
                    runOnUiThread {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(
                            this@MainActivity,
                            "Failed to load data (code ${response.code})",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        })
    }
}
