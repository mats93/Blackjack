package no.blackjack.game

import no.blackjack.domain.Player
import no.blackjack.domain.Player.Name
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.*
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class BlackjackGameTest {
    companion object {
        const val resourcePath = "src/test/resources"
    }

    @Test
    fun `sam should be the winner for cards text input with CA, H9 and a score of 20`() {
        val game = BlackjackGame(
            listOf(Player(Name.SAM), Player(Name.DEALER)),
            "$resourcePath/cards.txt"
        )

        game.startGame().let {
            assertNotNull(it)
            assertEquals(Name.SAM, it.name)
            assertEquals("sam: CA, H9", it.returnNameAndHandOutput())
            assertEquals(20, it.calculateScoreOfHand())
        }
    }

    @Test
    fun `should throw FileNotFoundException when provided cards file is not present`() {
        assertThrows<FileNotFoundException> {
            BlackjackGame(
                listOf(Player(Name.SAM), Player(Name.DEALER)),
                "$resourcePath/${UUID.randomUUID()}"
            )
        }
    }

    @Test
    fun `should throw when provided cards file is empty`() {
        assertThrows<FileNotFoundException> {
            BlackjackGame(
                listOf(Player(Name.SAM), Player(Name.DEALER)),
                "$resourcePath/empty.txt}"
            )
        }
    }

    @Test
    fun `should throw IllegalArgumentException when provided cards file is unprocessable`() {
        assertThrows<IllegalArgumentException> {
            BlackjackGame(
                listOf(Player(Name.SAM), Player(Name.DEALER)),
                "$resourcePath/unprocessable.txt"
            )
        }
    }

    @Test
    fun `startGame when players are empty should return null`() {
        val game = BlackjackGame(listOf())
        assertThrows<IllegalArgumentException> {
            game.startGame()
        }
    }

    @Test
    fun `startGame when players are more than 2 should return null`() {
        val players = listOf(
            Player(Name.SAM),
            Player(Name.DEALER),
            Player(Name.SAM)
        )
        val game = BlackjackGame(players)
        assertThrows<IllegalArgumentException> {
            game.startGame()
        }
    }

    @Test
    fun `startGame should return null if sam is not present`() {
        val players = listOf(
            Player(Name.DEALER),
            Player(Name.DEALER)
        )
        val game = BlackjackGame(players)
        assertThrows<IllegalArgumentException> {
            game.startGame()
        }
    }

    @Test
    fun `startGame should return null if dealer is not present`() {
        val players = listOf(
            Player(Name.SAM),
            Player(Name.SAM)
        )
        val game = BlackjackGame(players)
        assertThrows<IllegalArgumentException> {
            game.startGame()
        }
    }
}