package com.stoe.currencyconverter.main

import com.stoe.currencyconverter.data.CurrencyApi
import com.stoe.currencyconverter.data.models.CurrencyResponse
import com.stoe.currencyconverter.util.Resource
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    private val api: CurrencyApi
) : MainRepository {

    override suspend fun getRates(): Resource<CurrencyResponse> {
        return try {
            val response = api.getRates()
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message()    )
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }

}