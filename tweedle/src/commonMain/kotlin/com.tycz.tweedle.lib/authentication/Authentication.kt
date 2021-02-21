package com.tycz.tweedle.lib.authentication

import com.tycz.tweedle.lib.api.AuthClient
import com.tycz.tweedle.lib.epochSeconds
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.auth.*
import io.ktor.http.auth.AuthScheme.OAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.tycz.tweedle.lib.api.Response

class Authentication {
    private val _client = AuthClient.instance

    suspend fun authenticateWithCallback(callbackUrl: String, apiKey: String, apiSecret: String):Response<String>? = withContext(Dispatchers.Default){

        val headerBuilder = StringBuilder()

        val epoch = epochSeconds()

        val allowedChars = ('A'..'Z') + ('a'..'z') + (0..9)
        val s = (1..32).map { allowedChars.random() }
            .joinToString("")

        val nonce = "${HttpAuthHeader.Parameters.OAuthNonce}=\"$s\","

        val consumerKey = "${HttpAuthHeader.Parameters.OAuthConsumerKey}=\"${apiKey}\""
        val callback = "${HttpAuthHeader.Parameters.OAuthCallback}=\"${callbackUrl}\""
        val method = "${HttpAuthHeader.Parameters.OAuthSignatureMethod}=\"HMAC-SHA1\""
        val version = "${HttpAuthHeader.Parameters.OAuthVersion}=\"1.0\""
        val timestamp = "${HttpAuthHeader.Parameters.OAuthTimestamp}=\"${epoch}\""

        val sigBuilder = SignatureBuilder()
        val authSig = sigBuilder.createSignature(SignatureParams(callback, apiKey, apiSecret, s, epoch))

        val signature = "${HttpAuthHeader.Parameters.OAuthSignature}=\"$authSig\""

        headerBuilder.append("$OAuth ")
        headerBuilder.append("$consumerKey,")
        headerBuilder.append("$callback,")
//        headerBuilder.append("oauth_consumer_secret=\"${apiSecret}\",")
        headerBuilder.append("$signature,")
        headerBuilder.append("$method,")
        headerBuilder.append("$version,")
        headerBuilder.append("$nonce,")
        headerBuilder.append("$timestamp,")

        val builder = HttpRequestBuilder()
        builder.header(HttpHeaders.Authorization, headerBuilder.toString())
        _client.post(builder)

    }
}