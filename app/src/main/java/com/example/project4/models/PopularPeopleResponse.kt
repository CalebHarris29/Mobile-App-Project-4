package com.example.project4.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PopularPeopleResponse(
    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<Actor>,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("total_results")
    val totalResults: Int
)
