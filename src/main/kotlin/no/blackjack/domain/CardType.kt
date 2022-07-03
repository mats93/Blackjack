package no.blackjack.domain

enum class CardType(val shortHand: String, val value: Int) {
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10),
    ACE("A", 11);

    companion object {
        @JvmStatic
        fun fromShorthand(shortHand: String): CardType {
            return when (shortHand) {
                "2" -> TWO
                "3" -> THREE
                "4" -> FOUR
                "5" -> FIVE
                "6" -> SIX
                "7" -> SEVEN
                "8" -> EIGHT
                "9" -> NINE
                "10" -> TEN
                "J" -> JACK
                "Q" -> QUEEN
                "K" -> KING
                "A" -> ACE
                else -> {
                    throw IllegalArgumentException("CardType '$shortHand' is not a defined type")
                }
            }
        }
    }
}