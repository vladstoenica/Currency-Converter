package com.stoe.currencyconverter.util

//daca e un success resource, atasam data fara mesaj, daca e eroare, atasam mesajul fara data
sealed class Resource<T>(val data: T?, val message: String?) {    //cannot be extended/inherited
    class Success<T>(data: T?) : Resource<T>(data, null)
    class Error<T>(message: String) : Resource<T>(null, message)

}