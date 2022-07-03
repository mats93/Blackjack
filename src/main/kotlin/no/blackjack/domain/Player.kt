package no.blackjack.domain

data class Player(
    val name: Name,
    val hand: MutableList<Card> = mutableListOf()
) {

    fun calculateScoreOfHand(): Int {
        var score = 0
        hand.forEach { card -> score += card.value }
        return score
    }

    enum class Name(val value: String) {
        SAM("sam"),
        DEALER("dealer")
    }

    fun returnNameAndHandOutput(): String {
        var formattedOutput = ""
        hand.forEach { card ->
            formattedOutput += " ${card.suit.shortHand}${card.type.shortHand},"
        }
        val formattedHand = formattedOutput.replace(",$".toRegex(), "")
        return "${name.value}:$formattedHand"
    }
}