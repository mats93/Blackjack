package no.blackjack.domain

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PlayerTest {

    @Test
    fun `calculateScoreOfHand should return 0 when empty`() {
        val player = Player(Player.Name.DEALER)

        assertEquals(0, player.calculateScoreOfHand())
    }

    @Test
    fun `calculateScoreOfHand should return expected score`() {
        var hand = mutableListOf(Card(Suit.SPADES, CardType.TWO))
        assertEquals(2, Player(Player.Name.SAM, hand).calculateScoreOfHand())

        hand = mutableListOf(
            Card(Suit.SPADES, CardType.FIVE),
            Card(Suit.CLUBS, CardType.EIGHT)
        )
        assertEquals(13, Player(Player.Name.SAM, hand).calculateScoreOfHand())

        hand = mutableListOf(
            Card(Suit.SPADES, CardType.JACK),
            Card(Suit.CLUBS, CardType.ACE)
        )
        assertEquals(21, Player(Player.Name.SAM, hand).calculateScoreOfHand())

        hand = mutableListOf(
            Card(Suit.SPADES, CardType.ACE),
            Card(Suit.CLUBS, CardType.ACE)
        )
        assertEquals(22, Player(Player.Name.SAM, hand).calculateScoreOfHand())
    }
}