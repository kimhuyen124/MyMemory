package com.example.mymemory.model

enum class BoardSize(val numCars: Int) {
    EASY( 8 ),
    MEDIUM(18),
    HARD(24);
    fun getWidth(): Int{
        return when (this){
            EASY -> 2
            MEDIUM -> 3
            HARD -> 4
        }
    }
    fun getHeight():Int{
        return numCars / getWidth()
    }
    fun getNumPairs(): Int{
        return numCars /2
    }
}