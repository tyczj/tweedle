package com.tycz.android.twitter.api.v2

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model: MainViewModel by viewModels()

        val token = ""
        //region Get Single Tweet
//        model.getTweet(token, 1299418846990921728).observe(this, Observer {
//            when(it){
//                is Response.Error -> {it.exception}
//                is Response.Success -> it.data
//            }
//        })
        //endregion

        //region Add Rule
//        val filters:MutableList<Add> = mutableListOf()
//        val filter: Filter = Filter.Builder()
//            .addOperator("#SundayMorning")
//            .and()
//            .setLanguage(Filter.ENGLISH)
//            .build()
//
//        val addRule = Add(filter.filter, "Sunday Morning")
//        filters.add(addRule)
//        val rule = Rule(filters)
//
//        model.addRule(token, rule).observe(this, Observer {
//            when(it){
//                is Response.Error -> {it.exception}
//                is Response.Success -> it.data
//            }
//        })
        //endregion

        //region Delete Rule
//        val filter: Filter = Filter.Builder()
//            .addOperator("#SundayMorning")
//            .and()
//            .setLanguage(Filter.ENGLISH)
//            .build()
//        val list = mutableListOf<String>(filter.filter)
//        val delete = Delete(list)
//        val deleteRule = DeleteRule(delete)
//
//        model.deleteRule(token,
//            deleteRule).observe(this, Observer {
//            when(it){
//                is Response.Error -> {it.exception}
//                is Response.Success -> it.data
//            }
//        })
        //endregion

        //region Streaming
//        model.getStreamTweets(
//            token
//        ).observe(this, Observer {
//            when(it){
//                is Response.Success -> Log.d("TWEET", it.data.data.text)
//            }
//        })
        //endregion

        //region Recent Tweets
        //        val map = HashMap<String, String>()
//        map["tweet.fields"] = "lang"
//        map["expansions"] = "attachments.media_keys"
//        map["media.fields"] = "preview_image_url,url"
//
//        model.getRecentTweets(token,
//            "from:TwitterDev",
//            map).observe(this, Observer {
//            when(it){
//                is Response.Error -> {it.exception}
//                is Response.Success -> it.data
//            }
//        })
        //endregion

    }
}