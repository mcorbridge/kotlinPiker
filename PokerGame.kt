/*
 * Copyright (c) 2020. Michael D. Corbridge
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains the property of Michael D. Corbridge. The intellectual and technical concepts contained herein are proprietary to Michael D. Corbridge and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained from Michael D. Corbridge.
 */

package com.mcorbridge.kotlinfirebase.cards

import com.mcorbridge.kotlinfirebase.Flatten

/**
 *
 * This static class is called from the 'PokerGame' class
 *
 * */
// TODO make sure that all the 'tied hands' scenarios are covered
class PokerGame {

    // Kotlin version of Java static class
    companion object {

        private var isTest = false // TRUE to test synthetic hands for the players

        private lateinit var deck: MutableList<Card>

        private var players: MutableList<Player> = mutableListOf(
            Player("Mike"),
            Player("Jan"),
            Player("Wally"),
            Player("Richard"),
            Player("Kevin")
        )

        fun testCreatePlayerHands(): MutableList<Card> {
            deck = mutableListOf()
            deck.addAll(Hearts().hearts)
            deck.addAll(Spades().spades)
            deck.addAll(Clubs().clubs)
            deck.addAll(Diamonds().diamonds)

            // custom shuffler via the 'swapmonster'!
            for (n in 0..100) {
                val rand0: Int = (0..51).random()
                val rand1: Int = (0..51).random()
                deck.swapmonster(rand0, rand1)
            }
            return deck
        }

        private fun createPlayerHands() {
            deck = mutableListOf()
            deck.addAll(Hearts().hearts)
            deck.addAll(Spades().spades)
            deck.addAll(Clubs().clubs)
            deck.addAll(Diamonds().diamonds)

            // custom shuffler via the 'swapmonster'!
            for (n in 0..100) {
                val rand0: Int = (0..51).random()
                val rand1: Int = (0..51).random()
                deck.swapmonster(rand0, rand1)
            }

            // A test hand if TRUE
            if (isTest) {

//                players[0].hand = HighCardTied.hand0
//                players[1].hand = HighCardTied.hand1
//                players[2].hand = HighCardTied.hand2
//                players[3].hand = HighCardTied.hand3
//                players[4].hand = HighCardTied.hand4

//                players[0].hand = PairTied.hand0
//                players[1].hand = PairTied.hand1
//                players[2].hand = PairTied.hand2
//                players[3].hand = PairTied.hand3
//                players[4].hand = PairTied.hand4

//                players[0].hand = TwoPairTied.hand0
//                players[1].hand = TwoPairTied.hand1
//                players[2].hand = TwoPairTied.hand2
//                players[3].hand = TwoPairTied.hand3
//                players[4].hand = TwoPairTied.hand4

//                players[0].hand = StraightTied.hand0
//                players[1].hand = StraightTied.hand1
//                players[2].hand = StraightTied.hand2
//                players[3].hand = StraightTied.hand3
//                players[4].hand = StraightTied.hand4

//                players[0].hand = FullHouseTied.hand0
//                players[1].hand = FullHouseTied.hand1
//                players[2].hand = FullHouseTied.hand2
//                players[3].hand = FullHouseTied.hand3
//                players[4].hand = FullHouseTied.hand4

//                players[0].hand = Flush4WayTied.hand0
//                players[1].hand = Flush4WayTied.hand1
//                players[2].hand = Flush4WayTied.hand2
//                players[3].hand = Flush4WayTied.hand3
//                players[4].hand = Flush4WayTied.hand4

//                players[0].hand = FiveFlushTied.hand0
//                players[1].hand = FiveFlushTied.hand1
//                players[2].hand = FiveFlushTied.hand2
//                players[3].hand = FiveFlushTied.hand3
//                players[4].hand = FiveFlushTied.hand4

//                players[0].hand = StraightFlushTied.hand0
//                players[1].hand = StraightFlushTied.hand1
//                players[2].hand = StraightFlushTied.hand2
//                players[3].hand = StraightFlushTied.hand3
//                players[4].hand = StraightFlushTied.hand4

//                players[0].hand = RoyalFlushTied.hand0
//                players[1].hand = RoyalFlushTied.hand1
//                players[2].hand = RoyalFlushTied.hand2
//                players[3].hand = RoyalFlushTied.hand3
//                players[4].hand = RoyalFlushTied.hand4

            } else { // deal each player 5 cards
                for (player in players) {
                    player.hand = getHand()
                }
            }

            // let's find out who the poker rank of each players hand!
            for (player in players) {
                HandAnalysis.findValue(player.hand)
            }
        }

        fun showDeck() {
            for (card in deck) {
                print("|${card.name}:${card.suit}")
            }
            println()
        }

        /**
         * This function is called after each player has been dealt 5 cards, and the poker rank
         * of each hand has been determined.  But who won?
         */
        fun findWinner(): Winner {
            //randomly seed each player with 5 cards from a newly shuffled deck
            createPlayerHands()

            val winner = Winner()

            val sortedPlayers: List<Player> = players.sortedWith(compareBy { it.hand.value }).sortedWith(compareBy { it.hand.rank })

            // this is based on no ties between hands
            val currentWinningPlayer: Player = sortedPlayers[4] // this will be further determined if tied

            // The last player in the sort has the highest score, but other players may have the same
            // combination of poker rank and cards (players having same pairs - 7's - , for example)
            // So before announcing a winner, check to make sure other players do not have the same
            // winning combination.  If a tie is found the winner will be decided by next highest card
            // in their hand.

            // If the actual hands are tied, the ranking of suits from highest to lowest is
            // spades, hearts, diamonds, clubs. Suits never break a tie for winning a pot. Suits are
            // used to break a tie between cards of the same rank (no redeal or redraw).
            // "Robert's Rules of Poker"

            val listTies: MutableList<Player> = mutableListOf()

            // find players who have tied hands - unlikely, but possible
            for (player in sortedPlayers) {
                println("|--------- ${player.name} --------- ${player.hand.highCards.toSortedMap()} --- ${player.hand.type}")
                if ((player.hand.rank == currentWinningPlayer.hand.rank) && (player.hand.value == currentWinningPlayer.hand.value)) {
                    listTies.add(player)
                }
            }

            // TODO add code to handle all types of poker hand ties (maybe move this into a utility class?)
            /**
             * High Card, Pair, Two Pair, Three of a Kind, Straight, Flush, Full House, Four of a Kind, Straight Flush, Royal Flush
             *    4X       2X      2X                        4X        4X                                      4X            4X
             */
            if (listTies.size > 1) {
                val tieBreaker: MutableList<String> = when (listTies[0].hand.type) {
                    "high card" -> doTiedHighCard(listTies)
                    "pair" -> doTiedPair(listTies)
                    "two pair" -> doTwoTiedPair(listTies)
                    "straight" -> doTiedStraight(listTies)
                    "flush" -> doTiedFlush(listTies)
                    "straight flush" -> doTiedStraightFlush(listTies)
                    "royal flush" -> doTiedRoyalFlush(listTies)
                    else -> mutableListOf("nada")
                }
                winner.name.add(tieBreaker[0]) // player name
                winner.winningHand = (tieBreaker[1]) // winning hand type (pair, two pair, straight, ...etc)
                winner.status = (tieBreaker[2]) // hand status (simple; from tie; from RRoP)
            } else { // there are no tied hands
                winner.name.add(currentWinningPlayer.name)// player name
                winner.winningHand = currentWinningPlayer.hand.type // winning hand type
                winner.status = "simple" // hand status

            }
            return winner
        }

        /**
         * handle situation where 2 or more players have tied 'High Card' (PH) cards (and possibly tied H1..H4 cards hands)
         */
        private fun doTiedHighCard(listTies: MutableList<Player>): MutableList<String> {
            var winnerName = "default"
            var isRRoP = true
            //** MUST BE IN [0,1,2] = [name, winning hand type, win deterined from ...] ORDER! (Yeah.. my bad)
            val tieInfo: MutableList<String> = mutableListOf()
            //step 1: mash all the player hands into one list (we have to keep track of which player has which card to do this)
            var concat: MutableList<Pair<String, Map.Entry<String, Pair<Int, String>>>> = mutableListOf()
            for (player in listTies) {
                for (card in player.hand.highCards) {
                    concat.add(Pair(player.name, card))
                }
            }

            concat.sortBy { it.second.value.first }
            concat.reverse()

            var groupBy = concat.groupBy { it.second.value.first }

            loop@ for (group in groupBy) {
                if(group.value.size == 1){
                    winnerName = group.value[0].first
                    isRRoP = false
                    break@loop
                }
            }

            if(isRRoP){
                tieInfo.add(findRobertsRuleHighCard(concat))
            }else{
                tieInfo.add(winnerName)
            }

            tieInfo.add("high card")
            tieInfo.add("determined from tied high card")

            return tieInfo
        }

        /**
         * Only two players can have the same tied pair, obviously
         * for example: 7D & 7C, 7H & 7S - thus the kicker must be determined
         */
        private fun doTiedPair(listTies: MutableList<Player>): MutableList<String> {
            var winnerName: String
            var isRRoP = true
            //** MUST BE IN [0,1,2] = [name, winning hand type, win deterined from] ORDER! (Yeah.. my bad. Should be a map?)
            val tieInfo: MutableList<String> = mutableListOf()

            val concat: MutableList<Pair<String, Map.Entry<String, Pair<Int, String>>>> = mutableListOf()
            for (player in listTies) {
                for (card in player.hand.highCards) {
                    concat.add(Pair(player.name, card))
                }
            }

            val hcKeys: List<String> = listOf("H1", "H2", "H3")
            for (hckey in hcKeys) {
                val tmp: MutableList<List<Pair<String, Map.Entry<String, Pair<Int, String>>>>> = mutableListOf()
                for (group in concat.filter { it.second.key == hckey }.groupBy { it.second.value.first }) {
                    tmp.add(group.value)
                }
                // a hand has been found with a unique kicker
                if (tmp.size == listTies.size) {
                    winnerName = tmp.flatten().maxWithOrNull(compareBy { it.second.value.first })?.first ?: break
                    tieInfo.add(winnerName)
                    tieInfo.add("pair")
                    tieInfo.add("determined from tied pair")
                    isRRoP = false
                    break
                }
            }

            // the rare situation where two players have the same pair AND the same
            // remaining 3 cards
            if (isRRoP) {
                winnerName = findRobertsRuleHighCard(concat)
                tieInfo.add(winnerName)
                tieInfo.add("pair")
                tieInfo.add("determined from RRoP")
            }
            return tieInfo
        }

        /**
         * There can only ever be ONE situation where two players have the the same tied pairs.
         * for example: 7H & 7D ... and 7C & 7S
         * Thus, the kicker will be determined from the one remaining card in the hand: H1
         */
        private fun doTwoTiedPair(listTies: MutableList<Player>): MutableList<String> {
            var winnerName = "default"
            var isRRoP = true
            //** MUST BE IN [0,1,2] = [name, winning hand type, win deterined from ...] ORDER! (Yeah.. my bad)
            val tieInfo: MutableList<String> = mutableListOf()

            val concat: MutableList<Pair<String, Map.Entry<String, Pair<Int, String>>>> = mutableListOf()
            for (player in listTies) {
                for (card in player.hand.highCards) {
                    concat.add(Pair(player.name, card))
                }
            }

            val max = concat.filter { it.second.key == "H1" }.maxOfOrNull { it.second.value.first }
            val ans = concat.filter { it.second.value.first == max }

            if (ans.size == 1) {
                winnerName = ans[0].first
                isRRoP = false
            }

            if (isRRoP) { // the kickers in each hand are tied -> RRoP
                winnerName = findRobertsRuleHighCard(concat)
                tieInfo.add(winnerName)
                tieInfo.add("two pair") // <- the consequence of using unstructured objects to transfer info. Be careful!!!
                tieInfo.add("determined from RRoP tied two pair")
            } else {
                tieInfo.add(winnerName)
                tieInfo.add("two pair") // see above
                tieInfo.add("determined from tied two pair")
            }
            return tieInfo
        }

        /**
         * This theoretically *can* happen.  It was tested on a synthetic hand where 4 players have the identical Straight
         * Five cards in a sequence, but not of the same suit.
         * If hands are tied, they MUST all be the same value - just different suits
         * thus the concat list goes right to 'findRobertsRuleHighCard'
         * Chance of three, or four players drawing the same Straight?  Practically zero. Two? Less unlikely...
         */
        private fun doTiedStraight(listTies: MutableList<Player>): MutableList<String> {

            var winnerName = "default"
            var isRRoP = true
            val tieInfo: MutableList<String> = mutableListOf()

            val concat: MutableList<Pair<String, Map.Entry<String, Pair<Int, String>>>> = mutableListOf()
            for (player in listTies) {
                for (card in player.hand.highCards) {
                    concat.add(Pair(player.name, card))
                }
            }

            tieInfo.add(findRobertsRuleHighCard(concat)) // must be player name
            tieInfo.add("straight") // must be winning hand type
            tieInfo.add("determined from tie") // must be how win was determined
            return tieInfo
        }

        /**
         * When two or more players have the identical flush, the hands are determined to be tied, and a winner will be determined
         * by finding the 'kicker' - the next highest card in each players hand, and consequently the winner.  If even those
         * next highest cards are tied, then the algorithm moves to the 2nd highest card, and so on until a winner is found
         */
        private fun doTiedFlush(listTies: MutableList<Player>): MutableList<String> {
            var winnerName = "default"
            var isRRoP = true
            val tieInfo: MutableList<String> = mutableListOf()

            val concat: MutableList<Pair<String, Map.Entry<String, Pair<Int, String>>>> = mutableListOf()
            for (player in listTies) {
                for (card in player.hand.highCards) {
                    concat.add(Pair(player.name, card))
                }
            }
            // sort the concatenated list by card value, reverse it (high to low), then group them by card value
            val srtGrp = concat.sortedWith(compareBy { it.second.value.first }).reversed().groupBy { it.second.value.first }
            /*  logic: Consider the following synthetic (possible, but highly unlikely) hands, grouped by value:
              {8=[(Richard, PH=(8, spade)), (Wally, PH=(8, heart)), (Jan, PH=(8, club)), (Mike, PH=(8, diamond))],
              6=[(Richard, H1=(6, spade)), (Wally, H1=(6, heart)), (Jan, H1=(6, club)), (Mike, H1=(6, diamond))],
              5=[(Richard, H2=(5, spade)), (Wally, H2=(5, heart)), (Jan, H2=(5, club)), (Mike, H2=(5, diamond))],
              4=[(Richard, H3=(4, spade)), (Wally, H3=(4, heart)), (Jan, H3=(4, club)), (Mike, H3=(4, diamond))],
              2=[(Richard, H4=(2, spade)), (Wally, H4=(2, heart)), (Jan, H4=(2, club)), (Mike, H4=(2, diamond))]}
              We can see that all four players have the exact same hand and RRoP must be applied
              Now consider the following synthetic hand where three are tied via high-card
             {14=[(Kevin, PH=(14, spade)), (Richard, PH=(14, heart)), (Wally, PH=(14, diamond))],
              13=[(Kevin, H1=(13, spade)), (Richard, H1=(13, heart))],
              12=[(Richard, H2=(12, heart))], <----- ONE!!!!!!! The first (highest) group of size == 1. Therefore WINNER!
              10=[(Kevin, H2=(10, spade)), (Richard, H3=(10, heart)), (Wally, H1=(10, diamond))],
              8=[(Wally, H2=(8, diamond))], <--- excluded because a group of size == 1 has been determined
              7=[(Kevin, H3=(7, spade))], <--- ditto ^
              6=[(Kevin, H4=(6, spade)), (Richard, H4=(6, heart)), (Wally, H3=(6, diamond))],
              3=[(Wally, H4=(3, diamond))]}
              Three players have a flush with the same highcard (ace), and two have the same next-highest (King)
              It isn't until the third highest card is examined that 'Richard' is shown to have the ultimate next-highest card
              This is evident by the size of the group: value.size == 1 Thus we have a winner of name: value.first().first
              */

            Loop@ for (value in srtGrp.values) {
                if (value.size == 1) {
                    winnerName = value.first().first
                    isRRoP = false
                    break@Loop
                }
            }

            if (isRRoP) {
                tieInfo.add(findRobertsRuleHighCard(concat, testFive = true)) // (PH,H1,H2,H3,H4) vs (H1,H2,H3,H4)
                tieInfo.add("flush")
                tieInfo.add("determined from RRoP tied flush")
            } else {
                tieInfo.add(winnerName)
                tieInfo.add("flush")
                tieInfo.add("determined from tied flush")
            }
            return tieInfo
        }

        /**
         * VERY, Very, very low probability
         */
        private fun doTiedStraightFlush(listTies: MutableList<Player>): MutableList<String> {

            var winnerName = "default"
            var isRRoP = true
            val tieInfo: MutableList<String> = mutableListOf()

            val concat: MutableList<Pair<String, Map.Entry<String, Pair<Int, String>>>> = mutableListOf()
            for (player in listTies) {
                for (card in player.hand.highCards) {
                    concat.add(Pair(player.name, card))
                }
            }

            tieInfo.add(findRobertsRuleHighCard(concat)) // must be player name
            tieInfo.add("straight flush") // must be winning hand type
            tieInfo.add("determined from RRoP tie") // must be how win was determined
            return tieInfo
        }

        /**
         * Likelihood of this happening? Practically zero.
         */
        private fun doTiedRoyalFlush(listTies: MutableList<Player>): MutableList<String> {
            var winnerName = "default"
            var isRRoP = true
            val tieInfo: MutableList<String> = mutableListOf()

            val concat: MutableList<Pair<String, Map.Entry<String, Pair<Int, String>>>> = mutableListOf()
            for (player in listTies) {
                for (card in player.hand.highCards) {
                    concat.add(Pair(player.name, card))
                }
            }

            tieInfo.add(findRobertsRuleHighCard(concat)) // must be player name
            tieInfo.add("royal flush") // must be winning hand type
            tieInfo.add("determined from RRoP tie") // must be how win was determined
            return tieInfo
        }


        /**
         * Roberts Rules of Poker
         * Yeah, this looks confusing. Here's what's happening: the function is passed a list composed of the
         * '{player name}:{card data}'.  The function is looking for the player who has the highest value spade.  If no
         * spade is available for comparison, we move on to the highest value heart, then diamond, and finally club
         */
        private fun findRobertsRuleHighCard(arg0: MutableList<Pair<String, Map.Entry<String, Pair<Int, String>>>>, testFive: Boolean = false): String {
            val rropList: List<String> = listOf("spade", "heart", "diamond", "club")
            val hcList: List<String>

            if (testFive) {
                hcList = listOf("PH", "H1", "H2", "H3", "H4") // where PH = Poker Hand - the card that designates the value of the hand
            } else {
                hcList = listOf("H1", "H2", "H3", "H4") // PH is not required since we are looking for the kicker
            }
            var winnerName = "default"

            loop@ for (suit in rropList) {
                for (hc in hcList) {
                    var tmp = arg0.filter { it.second.value.second == suit && it.second.key == hc }
                    if (!tmp.isEmpty()) {
                        var max = tmp.maxByOrNull { it.second.value.first }
                        if (max != null) {
                            winnerName = max.first
                        }
                        break@loop
                    }
                }
            }
            return winnerName
        }

        private fun <T> MutableList<T>.swapmonster(index1: Int, index2: Int) {
            val tmp = this[index1] // 'this' corresponds to the list
            this[index1] = this[index2]
            this[index2] = tmp
        }

        private fun getCard(): Card {
            val deckSize: Int = deck.size - 1
            val rand: Int = (0..deckSize).random()
            val card: Card = deck[rand]
            deck.removeAt(rand)
            return card
        }

        private fun getHand(): Hand {
            val hand = Hand()
            for (n in 0..4) {
                val card = getCard()
                hand.cards.add(card)
            }
            return hand
        }

        fun flatten() {
            val flat = Flatten()
        }

    } // end class
}

/**
 *
 */
class Hand {
    val cards: MutableList<Card> = mutableListOf()
    var value: Int = 0 //<- based on the high cards in the poker hand - used to break ties
    var type: String = "default"
    var rank: Int = 0 //<- based on the hierarchy of poker hands: High Card=1..Royal Flush=10
    lateinit var highCards: MutableMap<String, Pair<Int, String>>
}// end class

/**
 *
 */
class Player(val name: String) {
    lateinit var hand: Hand

}// end class

/**
 *
 */
class HandAnalysis {

    companion object {

        private lateinit var sortedHand: List<Card>
        private lateinit var hand: Hand

        fun findValue(hand: Hand) {

            this.hand = hand

            sortHand()

            // cascade throught the possible poker hands
            findHighCard() // every hand will at least be have a High Card
            findPair()
            findTwoPair()
            findThreeOfKind()
            findStraight()
            findFlush()
            findFullHouse()
            findFourOfKind()
            findStraightFlush()
            findRoyalFlush()
        }

        private fun sortHand() {
            sortedHand = hand.cards.sortedWith(compareBy { it.value }).reversed()
        }

        /* High Card 0.995 : 1
        When you haven't made any of the hands below, the highest card plays.*/
        private fun findHighCard() {
            hand.value = sortedHand[0].value
            hand.type = "high card"
            hand.rank = 1

            hand.highCards = mutableMapOf()
            hand.highCards["PH"] = Pair(sortedHand[0].value, sortedHand[0].suit)
            hand.highCards["H1"] = Pair(sortedHand[1].value, sortedHand[1].suit)
            hand.highCards["H2"] = Pair(sortedHand[2].value, sortedHand[2].suit)
            hand.highCards["H3"] = Pair(sortedHand[3].value, sortedHand[3].suit)
            hand.highCards["H4"] = Pair(sortedHand[4].value, sortedHand[4].suit)
        }

        /* Pair 1.366 : 1
        Two cards of the same rank.*/
        private fun findPair() {
            var isPair = false

            val frequenciesByValue: Map<Int, Int> =
                sortedHand.groupingBy { it.value }.eachCount()

            var numPairs = 0
            var pairValue = 0

            for (it in frequenciesByValue) {
                if (it.value == 2) {
                    hand.value = it.key //< --- hand value
                    pairValue = it.key
                    numPairs++
                }
            }

            val nextHighest: List<String> = listOf("H1", "H2", "H3", "H4", "H5", "H6")
            var n = 0

            if (numPairs == 1) {
                isPair = true
            }

            if (isPair) {
                //hand.value determined above ^^
                hand.highCards = mutableMapOf()
                for (card in sortedHand) {
                    if (card.value == pairValue) {
                        hand.highCards["PH"] = Pair(card.value, card.suit)
                    } else {
                        //card.pokerValue = nextHighest[n]
                        hand.highCards[nextHighest[n]] = Pair(card.value, card.suit)
                        n++
                    }
                }
                hand.type = "pair"
                hand.rank = 2
            }
        }

        /* Two pair 20 : 1
         Two different pairs.*/
        private fun findTwoPair() {
            var isTwoPair = false

            val frequenciesByValue: Map<Int, Int> =
                sortedHand.groupingBy { it.value }.eachCount()

            var numPairs = 0
            var maxPairValue = 0

            for (it in frequenciesByValue) {
                if (it.value == 2) {
                    numPairs++
                    if (it.key > maxPairValue) {
                        maxPairValue = it.key // <- finding the pair having the highest value
                    }
                }
            }

            // here we annotate the two pairs as the greater (PH1) one, and lesser one (PH2)
            // plus a high card to allow hands of pairs to be rated
            if (numPairs == 2) {
                isTwoPair = true
                hand.highCards = mutableMapOf()
                val pk1 = (frequenciesByValue.filterValues { it == 2 }.keys).toIntArray()[0]
                val pk2 = (frequenciesByValue.filterValues { it == 2 }.keys).toIntArray()[1]
                for (card in sortedHand) {
                    if (card.value == pk1) {
                        //card.pokerValue = "PH1"
                        hand.highCards["PH1"] = Pair(card.value, card.suit)
                    } else if (card.value == pk2) {
                        //card.pokerValue = "PH2"
                        hand.highCards["PH2"] = Pair(card.value, card.suit)
                    } else {
                        //card.pokerValue = "H1"
                        hand.highCards["H1"] = Pair(card.value, card.suit)
                    }
                }
            }

            if (isTwoPair) {
                hand.value = maxPairValue
                hand.type = "two pair"
                hand.rank = 3
            }
        }

        /* Three of a kind 46 : 1
        Three cards of the same rank.*/
        private fun findThreeOfKind() {
            var isThreeOfKind = false

            val frequenciesByValue: Map<Int, Int> =
                sortedHand.groupingBy { it.value }.eachCount()

            var toak = 0 // 'three of a kind' = toak

            if (frequenciesByValue.values.contains(3)) {
                isThreeOfKind = true
                for (it in frequenciesByValue) {
                    if (it.value == 3) {
                        hand.value = it.key // value of card in the triple
                    }
                }
                toak = (frequenciesByValue.filterValues { it == 3 }.keys).toIntArray()[0]
            }

            val hList: Array<String> = arrayOf("H1", "H2")
            var n = 0

            if (isThreeOfKind) {
                //hand.value determined above ^^^
                hand.highCards = mutableMapOf()
                hand.type = "three of a kind"
                hand.rank = 4
                for (card in sortedHand) {
                    if (card.value == toak) {
                        //card.pokerValue = "PH"
                        hand.highCards["PH"] = Pair(card.value, card.suit)
                    } else {
                        //card.pokerValue = hList[n]
                        hand.highCards[hList[n]] = Pair(card.value, card.suit)
                        n++
                    }
                }
            }
        }

        /* Straight 254 : 1
        Five cards in a sequence, but not of the same suit.*/
        private fun findStraight() {
            var isStraight = false

            if (sortedHand[4].value == sortedHand[3].value - 1 &&
                sortedHand[3].value == sortedHand[2].value - 1 &&
                sortedHand[2].value == sortedHand[1].value - 1 &&
                sortedHand[1].value == sortedHand[0].value - 1
            ) {
                isStraight = true
            }

            if (isStraight) {
                hand.value = sortedHand[4].value //<- highest card in sequence
                hand.type = "straight"
                hand.rank = 5
                hand.highCards = mutableMapOf()
                hand.highCards["H4"] = Pair(sortedHand[4].value, sortedHand[4].suit)
                hand.highCards["H3"] = Pair(sortedHand[3].value, sortedHand[3].suit)
                hand.highCards["H2"] = Pair(sortedHand[2].value, sortedHand[2].suit)
                hand.highCards["H1"] = Pair(sortedHand[1].value, sortedHand[1].suit)
                hand.highCards["PH"] = Pair(sortedHand[0].value, sortedHand[0].suit) // top card in straight
            }
        }

        /* Flush 509 : 1
        Any five cards of the same suit, but not in a sequence.*/
        private fun findFlush() {
            var isFlush = false

            val frequenciesBySuit: Map<String, Int> =
                sortedHand.groupingBy { it.suit }.eachCount()

            if (frequenciesBySuit.size == 1) {
                isFlush = true
            }

            if (isFlush) {
                hand.value = sortedHand[0].value //<- higest card in flush
                hand.type = "flush"
                hand.rank = 6
                hand.highCards = mutableMapOf()
                hand.highCards["H4"] = Pair(sortedHand[4].value, sortedHand[4].suit)
                hand.highCards["H3"] = Pair(sortedHand[3].value, sortedHand[3].suit)
                hand.highCards["H2"] = Pair(sortedHand[2].value, sortedHand[2].suit)
                hand.highCards["H1"] = Pair(sortedHand[1].value, sortedHand[1].suit)
                hand.highCards["PH"] = Pair(sortedHand[0].value, sortedHand[0].suit)
            }
        }

        /* Full house 693 : 1
        Three of a kind with a pair.*/
        private fun findFullHouse() {
            var isFullHouse = false

            val frequenciesByValue: Map<Int, Int> =
                sortedHand.groupingBy { it.value }.eachCount()


            if (frequenciesByValue.size == 2) {
                if (frequenciesByValue.values.contains(2) && frequenciesByValue.values.contains(3)) {
                    isFullHouse = true
                }
                for (it in frequenciesByValue) {
                    if (it.value == 3) {
                        hand.value = it.key // value of card in the triple
                    }
                }
            }

            if (isFullHouse) {
                // hand.value determined above^^
                hand.highCards = mutableMapOf()
                hand.type = "full house"
                hand.rank = 7
                for (card in sortedHand) {
                    if (card.value == hand.value) {
                        //card.pokerValue = "PH1" // used to compare two players having a full house
                        hand.highCards["PH1"] = Pair(card.value, card.suit)
                    } else {
                        //card.pokerValue = "PH2" // used to break (or confirm) a tie hand
                        hand.highCards["PH2"] = Pair(card.value, card.suit)
                    }
                }
            }
        }

        /* Four of a kind 4,165 : 1
        All four cards of the same rank.*/
        private fun findFourOfKind() {
            var isFourOfKind = false

            val frequenciesByValue: Map<Int, Int> =
                sortedHand.groupingBy { it.value }
                    .eachCount() // <- Four of a Kind Map.size = 2, one has a key of value 4

            //(foak = four of a kind)
            var foakKey: Int = 0

            if (frequenciesByValue.containsValue(4)) {
                isFourOfKind = true
                // here we determine the value of the card that is part of the 'Four of a Kind'
                foakKey = (frequenciesByValue.filterValues { it == 4 }.keys).toIntArray()[0]
            }

            // I know this could have been condensed, but sometime more (code) is better!
            if (isFourOfKind) {
                hand.highCards = mutableMapOf()
                hand.value = sortedHand[4].value //<- value of card in four of a kind
                hand.type = "four of a kind"
                hand.rank = 8
                for (card in sortedHand) {
                    if (card.value == foakKey) {
                        //card.pokerValue = "PH"
                        hand.highCards["PH"] = Pair(card.value, card.suit)
                    } else {
                        //card.pokerValue = "H1"
                        hand.highCards["H1"] = Pair(card.value, card.suit)
                    }
                }
            }
        }

        /* Straight flush 72,192 : 1
        Five cards in a sequence, all in the same suit.*/
        private fun findStraightFlush() {
            var isStraightFlush = false

            val frequenciesBySuit: Map<String, Int> =
                sortedHand.groupingBy { it.suit }
                    .eachCount() // <- Straight Flush will have only ONE suite Map.size = 1

            if (frequenciesBySuit.size == 1) {
                if (sortedHand[4].value == sortedHand[3].value - 1 &&
                    sortedHand[3].value == sortedHand[2].value - 1 &&
                    sortedHand[2].value == sortedHand[1].value - 1 &&
                    sortedHand[1].value == sortedHand[0].value - 1
                ) {
                    isStraightFlush = true
                }
            }
            if (isStraightFlush) {
                hand.value =
                    sortedHand[4].value //<- value of highest card in straight flush
                hand.type = "straight flush"
                hand.rank = 9
                hand.highCards = mutableMapOf()
                hand.highCards["H4"] = Pair(sortedHand[4].value, sortedHand[4].suit)
                hand.highCards["H3"] = Pair(sortedHand[3].value, sortedHand[3].suit)
                hand.highCards["H2"] = Pair(sortedHand[2].value, sortedHand[2].suit)
                hand.highCards["H1"] = Pair(sortedHand[1].value, sortedHand[1].suit)
                hand.highCards["PH"] = Pair(sortedHand[0].value, sortedHand[0].suit)
            }
        }

        /* Royal flush 649,739 : 1
         A, K, Q, J, 10, all the same suit.*/
        private fun findRoyalFlush() {
            var isRoyalFlush = false

            val frequenciesBySuit: Map<String, Int> =
                sortedHand.groupingBy { it.suit }
                    .eachCount() // <- Royal Flush will have only ONE suite Map.size == 1

            if (frequenciesBySuit.size == 1) {
                if (sortedHand[0].name == "ace" &&
                    sortedHand[1].name == "king" &&
                    sortedHand[2].name == "queen" &&
                    sortedHand[3].name == "jack" &&
                    sortedHand[4].name == "10"
                ) {
                    isRoyalFlush = true
                }
            }
            if (isRoyalFlush) {
                hand.value = 100 //<- no one can beat a Royal Flush
                hand.type = "royal flush"
                hand.rank = 10
                hand.highCards = mutableMapOf()
                hand.highCards["H1"] = Pair(sortedHand[0].value, sortedHand[0].suit)
                hand.highCards["H2"] = Pair(sortedHand[1].value, sortedHand[1].suit)
                hand.highCards["H3"] = Pair(sortedHand[2].value, sortedHand[2].suit)
                hand.highCards["H4"] = Pair(sortedHand[3].value, sortedHand[3].suit)
                hand.highCards["H5"] = Pair(sortedHand[4].value, sortedHand[4].suit)
            }
        }

    } //end companion object
}// end class