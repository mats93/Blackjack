package no.blackjack.domain

data class Card(
    val suit: Suit,
    val type: CardType,
) {
    val value = type.value

    override fun toString(): String {
        return "${suit.shortHand}${type.shortHand}"
    }
}