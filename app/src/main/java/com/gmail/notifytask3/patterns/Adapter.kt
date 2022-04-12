package com.gmail.notifytask3.patterns

class Adapter {

    fun main() {

        val thermometer = Thermometer()

        thermometer.isCold(Celsius(10.00))
        thermometer.isCold(Celsius(-10.00))

        val fahrenheitCold = Fahrenheit(10.00)
        val fahrenheitHot = Fahrenheit(110.00)

        //thermometer.isCold(fahrenheitCold)
        //thermometer.isCold(fahrenheitHot)

        val fahrenheitAdaptedCold = FahrenheitAdapter(fahrenheitCold)
        val fahrenheitAdaptedHot = FahrenheitAdapter(fahrenheitHot)
        thermometer.isCold(fahrenheitAdaptedCold)
        thermometer.isCold(fahrenheitAdaptedHot)
    }
}

class Thermometer() {
    fun isCold(temperature: Celsius) {
        println(temperature.degree < 0)
    }
}

open class Celsius(var degree: Double) {
}

class Fahrenheit(var fahrenheitDegree: Double)

class FahrenheitAdapter(temperature: Fahrenheit) :
    Celsius((temperature.fahrenheitDegree - 32) * 5 / 9)
