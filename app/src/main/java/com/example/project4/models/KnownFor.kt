package com.example.project4.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
data class KnownFor(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("overview")
    val overview: String? = null,

    @SerializedName("media_type")
    val mediaType: String? = null,

    @SerializedName("release_date")
    val releaseDate: String? = null,

    @SerializedName("first_air_date")
    val firstAirDate: String? = null,

    @SerializedName("vote_average")
    val voteAverage: Double? = null,

    @SerializedName("poster_path")
    val posterPath: String? = null
) : Serializable {

    val displayTitle: String
        get() = title ?: name ?: "Unknown Title"

    val displayDate: String
        get() = releaseDate ?: firstAirDate ?: ""

    val mediaTypeCapitalized: String
        get() = mediaType?.replaceFirstChar { it.uppercase() } ?: "Media"
}
