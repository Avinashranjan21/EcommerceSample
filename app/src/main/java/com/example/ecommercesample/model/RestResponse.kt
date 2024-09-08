package com.example.ecommercesample.model

import com.google.gson.annotations.SerializedName

data class Section(
    @SerializedName("sectionType") val sectionType: SectionType,
    @SerializedName("items") val items: List<Item>
)

data class Item(
    @SerializedName("title") val title: String,
    @SerializedName("image") val image: String
)

enum class SectionType {
    @SerializedName("banner")
    BANNER,

    @SerializedName("horizontalFreeScroll")
    HORIZONTAL_FREE_SCROLL,

    @SerializedName("splitBanner")
    SPLIT_BANNER
}

