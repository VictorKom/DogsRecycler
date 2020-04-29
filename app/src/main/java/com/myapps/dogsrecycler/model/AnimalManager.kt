package com.myapps.dogsrecycler.model

import com.myapps.dogsrecycler.common.App
import rx.Observable

class AnimalManager(private val animal: String) {

    fun getDogs() : Observable<ArrayList<String>> {
        return Observable.create {
            subscriber ->

                val api = App.getAnimalsAPI()
                val response = api.getDogs(animal,5).execute()
                if (response.isSuccessful) {
                    subscriber.onNext(response.body())
                    subscriber.onCompleted()
                } else {
                    subscriber.onError(Throwable(response.message()))
                }
        }
    }
}