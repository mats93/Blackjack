package no.blackjack.game

import no.blackjack.domain.Card
import no.blackjack.domain.DeckOfCards
import no.blackjack.domain.Player
import no.blackjack.domain.Player.Name
import java.util.*
import kotlin.streams.toList

class BlackjackGame(
    private val players: List<Player>,
    cardsFileLocation: String? = null
) {
    // Creates a single deck of playing cards
    private val deck = DeckOfCards(cardsFileLocation)

    fun startGame(): Player? {
        // Sam plays as the opponent against the dealer
        val opponent = players.find { player -> player.name == Name.SAM }
        val dealer = players.find { player -> player.name == Name.DEALER }

        // Only allow 2 players, the opponent and the dealer
        if (players.size != 2 || opponent == null || dealer == null) {
            throw IllegalArgumentException("need 2 players, sam and dealer")
        }

        playFirstRound(opponent, dealer)?.let {
            // If there is a winner after the first round, return it
            return it
        }

        // Plays the remaining rounds and returns the winner
        return playRemainingRounds(opponent, dealer)
    }

    private fun playFirstRound(opponent: Player, dealer: Player): Player? {
        // Each player is given two cards from the top of the shuffled deck of cards
        // The opponent is the first to draw
        for (i in 1..2) {
            opponent.hand.add(getTopCardFromDeck())
            dealer.hand.add(getTopCardFromDeck())
        }

        blackjackWinnerRoundOne()?.let {
            return it
        }

        aceWinnerRoundOne()?.let {
            return it
        }

        // There was a tie
        return null
    }

    // Checks if any players have blackjack return the winner or null
    private fun blackjackWinnerRoundOne(): Player? {
        var maybeWinner = Optional.empty<Player>()
        this.players.forEach { player ->
            if (player.calculateScoreOfHand() == 21) {
                // Update winner
                if (maybeWinner.isPresent && player.name != Name.DEALER) {
                    maybeWinner = Optional.of(player)
                } else if (maybeWinner.isEmpty) {
                    maybeWinner = Optional.of(player)
                }
            }
        }

        return maybeWinner.orElse(null)
    }

    // Return the dealer if both players have 2 aces (22 score) or null
    private fun aceWinnerRoundOne(): Player? {
        val numberOfPlayersWithTwoAces = this.players.stream()
            .filter { player -> player.calculateScoreOfHand() == 22 }
            .toList()
            .size

        return if (numberOfPlayersWithTwoAces == 2) {
            this.players.find { player: Player -> player.name == Name.DEALER }
        } else {
            null
        }
    }

    // Returns the winner in a list or null if there is a tie
    private fun playRemainingRounds(opponent: Player, dealer: Player): Player? {
        // Opponent draws from the until their total reaches 17 or higher
        while (opponent.calculateScoreOfHand() < 17) {
            opponent.hand.add(getTopCardFromDeck())
        }

        // Opponent has lost the game if their total is higher than 21 and the dealer win
        if (opponent.calculateScoreOfHand() > 21) {
            return dealer
        }

        // The dealer starts drawing cards when the opponent is done until their total is higher than the opponent
        while (dealer.calculateScoreOfHand() <= opponent.calculateScoreOfHand()) {
            dealer.hand.add(getTopCardFromDeck())
        }

        // Opponent wins if the dealer has a score higher than 21
        if (dealer.calculateScoreOfHand() > 21) {
            return opponent
        }

        // Determine who the winner is
        return if (opponent.calculateScoreOfHand() > dealer.calculateScoreOfHand()) {
            opponent
        } else if (opponent.calculateScoreOfHand() == dealer.calculateScoreOfHand()) {
            // tie
            null
        } else {
            dealer
        }
    }

    private fun getTopCardFromDeck(): Card {
        return deck.cards.removeFirst()
    }
}