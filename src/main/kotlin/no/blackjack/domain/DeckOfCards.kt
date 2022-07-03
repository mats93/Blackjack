package no.blackjack.domain

import java.io.*
import java.nio.charset.*

class DeckOfCards(
    cardsFileLocation: String? = null
) {
    var cards: MutableList<Card> = mutableListOf()

    init {
        if (cardsFileLocation.isNullOrEmpty()) {
            generateDeckOfCards()
            this.cards.shuffle()
        } else {
            getDeckFromFile(cardsFileLocation)
        }
    }

    private fun getDeckFromFile(cardsFileLocation: String) {
        val content: String = try {
            File(cardsFileLocation).readLines(Charset.defaultCharset())[0]
        } catch (ex: FileNotFoundException) {
            throw FileNotFoundException("file $cardsFileLocation is not present")
        } catch (ex: IndexOutOfBoundsException) {
            throw FileNotFoundException("file $cardsFileLocation is empty")
        }

        try {
            populateDeckOfCards(content)
        } catch (ex: IllegalArgumentException) {
            throw ex
        }
    }

    private fun generateDeckOfCards() {
        var cardsString = ""
        for (i in suitsArray.indices) {
            for (j in cardsArray.indices) {
                val suit = Suit.fromShorthand(suitsArray[i])
                val type = CardType.fromShorthand(cardsArray[j])
                cardsString += "${suit.shortHand}${type.shortHand}, "
            }
        }
        populateDeckOfCards(cardsString)
    }

    private fun populateDeckOfCards(cardString: String) {
        for (entry in cardString.replace(" ", "").split(",")) {
            if (entry.isNotEmpty()) {
                val suit = Suit.fromShorthand(entry.substring(0, 1))
                val type = CardType.fromShorthand(entry.substring(1))
                this.cards.add(Card(suit, type))
            }
        }
    }

    companion object {
        val suitsArray = arrayOf("C", "D", "H", "S")
        val cardsArray = arrayOf("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A")
    }
}