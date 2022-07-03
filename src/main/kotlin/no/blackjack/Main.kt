package no.blackjack

import no.blackjack.domain.Player
import no.blackjack.domain.Player.Name
import no.blackjack.game.BlackjackGame
import java.io.*
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    // Sam will play against the Dealer
    val players = listOf(
        Player(Name.SAM),
        Player(Name.DEALER)
    )

    // Starts the game, returns the winner or null if there is a tie
    val file = args.joinToString()
    val winner: Player? = try {
        BlackjackGame(players, if (file == "") null else file).startGame()
    } catch (ex: FileNotFoundException) {
        println("the provided file '$file' is either not found, empty or in the wrong format.\nExiting the program")
        exitProcess(1)
    } catch (ex: IllegalArgumentException) {
        println("the provided file '$file' has the wrong format. \nExiting the program")
        exitProcess(1)
    }

    // Prints the winner if any
    if (winner != null) {
        println(winner.name.value)
    } else {
        println("there was a tie, no winners")
    }

    // Prints the players and their hand
    players.forEach {
        println(it.returnNameAndHandOutput())
    }
}