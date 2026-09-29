package com.example.project4.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
data class Actor(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("profile_path")
    val profilePath: String? = null,

    @SerializedName("known_for_department")
    val knownForDepartment: String? = null,

    @SerializedName("popularity")
    val popularity: Double? = null,

    @SerializedName("gender")
    val gender: Int? = null,

    @SerializedName("known_for")
    val knownFor: List<KnownFor>? = null
) : Serializable {

    val fullImageUrl: String
        get() = if (!profilePath.isNullOrEmpty()) {
            "https://image.tmdb.org/t/p/w500$profilePath"
        } else {
            ""
        }

    val genderString: String
        get() = when (gender) {
            1 -> "Female"
            2 -> "Male"
            3 -> "Non-binary"
            else -> "Not Specified"
        }

    val knownForSummary: String
        get() {
            if (knownFor.isNullOrEmpty()) return "No known works listed."
            return knownFor.joinToString(separator = "\n") { item ->
                val rating = item.voteAverage?.let { String.format(java.util.Locale.US, "★ %.1f", it) } ?: ""
                val date = if (item.displayDate.isNotEmpty()) " (${item.displayDate.take(4)})" else ""
                "• ${item.displayTitle}$date - ${item.mediaTypeCapitalized} $rating".trim()
            }
        }

    val topWorkTitle: String
        get() = knownFor?.firstOrNull()?.displayTitle ?: "N/A"
}
