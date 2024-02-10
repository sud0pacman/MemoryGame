package com.example.mymemorygame.repository

class AppRepository private constructor() {
    companion object {
        private lateinit var instance: AppRepository

        fun getInstance(): AppRepository {
            if (!(::instance.isInitialized))
                instance = AppRepository()
            return instance
        }
    }
}