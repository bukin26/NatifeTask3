package com.gmail.notifytask3.patterns

class Singleton {

    companion object {

        @Volatile
        private var INSTANCE: Singleton? = null

        fun getInstance(): Singleton {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Singleton().also { INSTANCE = it }
            }
        }
    }
}