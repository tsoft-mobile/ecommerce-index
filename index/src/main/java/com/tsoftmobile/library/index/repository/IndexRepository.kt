package com.tsoftmobile.library.index.repository

import com.tsoftmobile.library.index.Config
import com.tsoftmobile.library.index.model.Resource
import com.tsoftmobile.library.index.model.response.IndexResponse
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class IndexRepository {
    fun fetchIndex(): Observable<Resource<IndexResponse>> {
        return Observable.create { emitter ->
            emitter.onNext(Resource.loading())
            val params = Config.getDefaultParams()
            params["new"] = true
            ApiClient.service.fetchIndex(params)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    emitter.onNext(Resource.success(it))
                    emitter.onComplete()
                }, {
                    emitter.onNext(Resource.error(it.message.toString()))
                    emitter.onComplete()
                })
        }
    }

}