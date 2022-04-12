package com.gmail.notifytask3.patterns

class Decorator {

    fun main() {
        val personal = Personal(Hostess(), Bartender(), Waiter())
        personal.serveGuests()
        personal.makeDrinks()
        personal.meetGuests()
    }
}

class Personal(
    private val hostess: Hostess,
    private val bartender: Bartender,
    private val waiter: Waiter,
) {

    fun serveGuests() {
        hostess.serveGuests()
        bartender.serveGuests()
        waiter.serveGuests()
    }

    fun makeDrinks() {
        bartender.makeDrinks()
    }

    fun meetGuests() {
        hostess.meetGuests()
    }
}

class Hostess {

    fun serveGuests() {
        println("Hostess is serving guests")
    }

    fun meetGuests() {
        println("Hostess is meeting guests")
    }
}

class Bartender {

    fun serveGuests() {
        println("Bartender is serving guests")
    }

    fun makeDrinks() {
        println("Bartender is making drinks")
    }
}

class Waiter {

    fun serveGuests() {
        println("Waiter is serving guests")
    }
}