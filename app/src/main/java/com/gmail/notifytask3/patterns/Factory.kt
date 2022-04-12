package com.gmail.notifytask3.patterns

class Factory {

    fun main() {
        val currency = MyFactory("USA").createCurrency()
        println(currency?.name())
    }
}

class MyFactory(private val country: String) {

    fun createCurrency(): Currency? {
        return when (country) {
            "USA" -> Dollar()
            "Italy" -> Euro()
            else -> null
        }
    }
}

interface Currency {
    fun symbol(): String
    fun name(): String
}

class Euro : Currency {

    override fun symbol(): String {
        return "€"
    }

    override fun name(): String {
        return "EUR"
    }
}

class Dollar : Currency {
    override fun symbol(): String {
        return "$"
    }

    override fun name(): String {
        return "USD"
    }
}