package com.stoe.currencyconverter.main

import com.stoe.currencyconverter.data.models.CurrencyResponse
import com.stoe.currencyconverter.util.Resource

interface MainRepository {  //interfata ca e mai usor sa testam viewmodels
    suspend fun getRates(): Resource<CurrencyResponse>
}