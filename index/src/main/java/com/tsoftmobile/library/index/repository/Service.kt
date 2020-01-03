package com.tsoftmobile.library.index.repository

import com.tsoftmobile.library.index.model.response.IndexResponse
import io.reactivex.Single
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.*

interface Service {
    @FormUrlEncoded
    @POST("mobile/index")
    fun fetchIndex(@FieldMap params: HashMap<String, Any>): Single<IndexResponse>
}
