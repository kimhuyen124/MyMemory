package com.example.mymemory.model

import com.example.mymemory.utils.DEFAULT_ICONS

class MemoryGame(private val boardSize: BoardSize) {


    val cards: List<MemoryCard>
    var numPairFoud = 0

    private var numCardFlips = 0
    private var indexOfSlinglelectedCard: Int ? = null
    init {
        val chosenImage = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
        val randomizedImages = (chosenImage + chosenImage).shuffled()
        cards = randomizedImages.map { MemoryCard(it) }
    }
        fun flipCard(position: Int): Boolean {
            numCardFlips++
            val card = cards[position]
//            There cases:
//            0 cards previously flipped over => flip over the selected card
//            1 card previously flipped over  => flip over the selected card + check if the images match
//            2 cards previously flipped over => restore cards+ flip over the selectd card
            var foundMath = false
            if (indexOfSlinglelectedCard == null){
//                0 or 2 cards previously flipped over
                restoreCards()
                indexOfSlinglelectedCard = position
            }else{
//                exectly 1 card previously flipped over
             foundMath = checkForMatch(indexOfSlinglelectedCard!!, position)
                indexOfSlinglelectedCard = null
            }
            card.isFaceUp = !card.isFaceUp
            return foundMath
        }
    private fun checkForMatch(position1: Int, position2: Int): Boolean {
        if (cards[position1].identifier != cards[position2].identifier) {
            return false
        }

        cards[position1].isMatched = true
        cards[position2].isMatched = true
        numPairFoud++
        return true

    }
    private fun restoreCards() {
        for (card in cards){
            if(!card.isMatched){
                card.isFaceUp = false
            }
        }
    }

    fun haveWonGame(): Boolean {
        return numPairFoud == boardSize.getNumPairs()
    }

    fun isCardFaceUp(position: Int): Boolean {
        return cards[position].isFaceUp
    }
    fun getNumMoves() : Int {
        return numCardFlips / 2
    }
}