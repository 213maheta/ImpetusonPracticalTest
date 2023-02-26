package com.impetuson.practicaltest.model

import com.google.gson.annotations.SerializedName

data class ResponseNews(
    @SerializedName("id"          ) var id          : Int?                = null,
    @SerializedName("title"       ) var title       : String?             = null,
    @SerializedName("url"         ) var url         : String?             = null,
    @SerializedName("imageUrl"    ) var imageUrl    : String?             = null,
    @SerializedName("newsSite"    ) var newsSite    : String?             = null,
    @SerializedName("summary"     ) var summary     : String?             = null,
    @SerializedName("publishedAt" ) var publishedAt : String?             = null,
    @SerializedName("updatedAt"   ) var updatedAt   : String?             = null,
    @SerializedName("featured"    ) var featured    : Boolean?            = null,
    @SerializedName("launches"    ) var launches    : ArrayList<Launches> = arrayListOf(),
    @SerializedName("events"      ) var events      : ArrayList<String>   = arrayListOf()
)

data class Launches (

    @SerializedName("id"       ) var id       : String? = null,
    @SerializedName("provider" ) var provider : String? = null

)
