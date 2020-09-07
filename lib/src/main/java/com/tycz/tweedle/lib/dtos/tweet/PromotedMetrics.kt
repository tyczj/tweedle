package com.tycz.tweedle.lib.dtos.tweet

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PromotedMetrics(
    @field:Json(name = "impression_count") val impression_count: Int?,
    @field:Json(name = "like_count") val like_count: Int?,
    @field:Json(name = "reply_count") val reply_count: Int?,
    @field:Json(name = "retweet_count") val retweet_count: Int?,
    @field:Json(name = "url_link_clicks") val url_link_clicks: Int?,
    @field:Json(name = "user_profile_clicks") val user_profile_clicks: Int?
)