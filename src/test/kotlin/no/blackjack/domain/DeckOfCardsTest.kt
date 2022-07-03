package no.blackjack.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.*
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DeckOfCardsTest {
    companion object {
        const val resourcePath = "src/test/resources"
    }

    @Test
    fun `should create card list when no file is provided`() {
        val deck = DeckOfCards()
        assertEquals(52, deck.cards.size)
    }

    @Test
    fun `cards should be shuffled when generated`() {
        val deck1 = DeckOfCards()
        val deck2 = DeckOfCards()

        // Could be equal but very unlikely, try up to 3 times anyway
        var isShuffled = false
        for (i in 0..2) {
            if (deck1.cards != deck2.cards) {
                isShuffled = true
            }
        }
        assertTrue(isShuffled)
    }

    @Test
    fun `should create card list from file when provided`() {
        val deck = DeckOfCards("$resourcePath/cards.txt")
        assertEquals("[CA, D5, H9, HQ, S8]", deck.cards.toString())
    }

    @Test
    fun `should fail and throw FileNotFoundException when file is provided but empty`() {
        assertThrows<FileNotFoundException> {
            DeckOfCards("$resourcePath/empty.txt")
        }
    }

    @Test
    fun `should fail and throw FileNotFoundException when file is provided but not found`() {
        assertThrows<FileNotFoundException> {
            DeckOfCards("$resourcePath/${UUID.randomUUID()}")
        }
    }

    @Test
    fun `should fail and throw IllegalArgumentException when file is provided but wrong format`() {
        assertThrows<IllegalArgumentException> {
            DeckOfCards("$resourcePath/unprocessable.txt")
        }
    }
}