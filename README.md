# Tweedle

[![v2](https://img.shields.io/endpoint?url=https%3A%2F%2Ftwbadges.glitch.me%2Fbadges%2Fv2)](https://developer.twitter.com/en/docs/twitter-api)
![version](https://img.shields.io/badge/version-0.5.1-blue)
<a href="https://twitter.com/tyczj" alt="Twitter">
<img src="https://img.shields.io/twitter/follow/tyczj?style=social" /></a>

***Library is currently in alpha and the API might change as the Twitter v2 API changes***

Tweedle is an Android library built around the [Twitter v2 API](https://developer.twitter.com/en/docs/twitter-api/early-access) built fully in Kotlin using Kotlin Coroutines

Usage

### ViewModel

```kotlin
class MainViewModel: ViewModel() {

    val oauth2 = OAuth2(token)
    private val _tweetLookup:TweetsLookup = TweetsLookup(oauth2)
    
    fun getTweet(tweetId:Long):LiveData<Response<SingleTweetPayload?>>{
            val liveData:MutableLiveData<Response<SingleTweetPayload?>> = MutableLiveData<Response<SingleTweetPayload?>>()
            viewModelScope.launch{
                val response = _tweetLookup.getTweet(tweetId)
                liveData.postValue(response)
            }
            return liveData
        }
}
```

### Activity

```kotlin
_viewModel.getTweet(1299418846990921728).observe(this, Observer {
    when(it){
        is Response.Error -> {it.exception}
        is Response.Success -> it.data
    }
})
```

## Streaming

Tweedle also supports streaming in real time of current tweets based on filters/rules applied

Creating a filter

You can easily create a filter with the filter builder

Say we want to stream tweets that come in for the hashtag `#SundayMorning` and only in english. That filter would look like this

```kotlin
val filters:MutableList<Add> = mutableListOf()
        val filter: Filter = Filter.Builder()
            .addOperator("#SundayMorning")
            .and()
            .setLanguage(Filter.ENGLISH)
            .build()
```

See additional examples [here](https://github.com/tyczj/Tweedle/blob/master/lib/src/test/java/com/tycz/tweedle/lib/FilterBuilderTest.kt) as well as the twiter documentation about creating filters [here](https://developer.twitter.com/en/docs/twitter-api/tweets/filtered-stream/integrate/build-a-rule)

### Adding a filter/rule 

(Streaming only supports OAuth2)

```kotlin
val oAuth2 = OAuth2(token)
val _tweetStream = TweetsStream(oAuth2)
val addRule = Add(filter.filter, "Sunday Morning")
filters.add(addRule)
val rule = Rule(filters)

_tweetStream.addRules(token, rule)
```

Currently Twitter only supports 25 filters/rules per API key

To start streaming tweets call the `startTweetStream` endpoint

```kotlin
_tweetStream.startTweetStream().collect {
    when(it){
         is Response.Success -> Log.d("TWEET", it.data.data.text)
    }
}
```

Collect is called every time a new tweet is received

To start using Tweedle, include the dependency in your `build.gradle`

Common
```kotlin
implementation("io.github.tyczj:tweedle:0.5.1")
```

Android
```kotlin
android{
    buildTypes {
        debug { matchingFallbacks = ['release'] } 
    }
}

implementation 'io.github.tyczj:tweedle-android:0.5.1'
```
