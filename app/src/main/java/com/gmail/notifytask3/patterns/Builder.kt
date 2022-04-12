package com.gmail.notifytask3.patterns

class Builder {

    fun main() {

        val myHouse = House.Builder()
            .withFloors(10)
            .withPool(true)
            .withColor("Grey")
            .build()
    }
}

class House private constructor(
    private var floors: Int,
    private var pool: Boolean,
    private var wallsColor: String
) {
    class Builder {
        private var floors = 1
        private var pool = false
        private var wallsColor = ""

        fun withFloors(floors: Int): Builder {
            if (floors > 0) this.floors = floors
            return this
        }

        fun withPool(pool: Boolean): Builder {
            this.pool = pool
            return this
        }

        fun withColor(wallsColor: String): Builder {
            this.wallsColor = wallsColor
            return this
        }

        fun build(): House {
            return House(floors, pool, wallsColor)
        }
    }
}