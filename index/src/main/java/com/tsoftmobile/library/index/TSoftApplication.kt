package com.tsoftmobile.library.index

import android.app.Application
import android.content.Context
import com.tsoftmobile.library.index.model.IndexItemType
import com.tsoftmobile.library.index.model.data.IndexItem
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

open class TSoftApplication : Application() {

    companion object {
        @JvmStatic
        lateinit var instance: TSoftApplication

        @JvmStatic
        lateinit var rxBus: RxBus

        @JvmStatic
        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        rxBus = RxBus()
    }

    inner class RxBus {

        private val bus = PublishSubject.create<Any>()

        fun send(event: Any) {
            bus.onNext(event)
        }


        fun hasObservers(): Boolean {
            return bus.hasObservers()
        }

        fun <T> toObservable(eventType: Class<T>): Observable<T> = bus.ofType(eventType)

    }

    object RxEvents {
        data class onItemClick(val type: IndexItemType, val data: Any?)
    }

}