package no.blackjack.domain

enum class Suit(val shortHand: String) {
    CLUBS("C"),
    DIAMONDS("D"),
    HEARTS("H"),
    SPADES("S");

    companion object {
        @JvmStatic
        fun fromShorthand(shortHand: String): Suit {
            return when (shortHand) {
                "C" -> CLUBS
                "D" -> DIAMONDS
                "H" -> HEARTS
                "S" -> SPADES
                else -> {
                    throw IllegalArgumentException("Suit '$shortHand' is not a defined suit")
                }
            }
        }
    }
}