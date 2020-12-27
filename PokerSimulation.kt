/*
 * Copyright (c) 2020. Michael D. Corbridge
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains the property of Michael D. Corbridge. The intellectual and technical concepts contained herein are proprietary to Michael D. Corbridge and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained from Michael D. Corbridge.
 */

package com.mcorbridge.kotlinfirebase.cards

import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * For the millionth time, a developer has created (recreated) a poker algorithm.
 * Why me then?  'Cause it is a good way to learn a new language (Kotlin).
 * There is a difference though, mine probably one of the worst.
 * UPDATE 12/25/2020: I ran the simulation 100,000 times!  Didn't crash!
 * Merry Christmas!
 */
// TODO track the percentage of the rank of the winning hands
class PokerSimulation {

    private var numMike: Int = 0
    private var numJan: Int = 0
    private var numWally: Int = 0
    private var numKevin: Int = 0
    private var numRichard: Int = 0

    private var numHighCard: Int = 0
    private var numOnePair: Int = 0
    private var numTwoPair: Int = 0
    private var numThreeKind: Int = 0
    private var numStraight: Int = 0
    private var numFlush: Int = 0
    private var numFullHouse: Int = 0
    private var numFourKind: Int = 0
    private var numStraightFlush: Int = 0
    private var numRoyalFlush: Int = 0

    private var numRuns: Int = 9 // var numRuns: Int = 0 (Zero) means the simulation runs ONCE

    // RESULTS OF 100,000 SIMULATED HANDS:
    // high card: 3,445
    // one pair: 64,279
    // two pair: 19,074
    // three of a kind: 9,725
    // straight: 1,691
    // flush: 965
    // full house: 680
    // four of a kind: 134
    // straight flush: 6
    // royal flush: 1

    private fun doPcnt(numPlayerWins: Int, numRuns: Int): String {
        val decimalFormat = DecimalFormat("##.#")
        decimalFormat.roundingMode = RoundingMode.UP
        return decimalFormat.format((numPlayerWins.toDouble() / (numRuns.toDouble() + 1.0)) * 100)
    }

    init {

        fun start() {

            for (n in 0..numRuns) run {
                val winner: Winner = PokerGame.findWinner() // each players hand has been dealt at this point
                when {
                    winner.name.contains("Mike") -> numMike++
                    winner.name.contains("Jan") -> numJan++
                    winner.name.contains("Wally") -> numWally++
                    winner.name.contains("Kevin") -> numKevin++
                    winner.name.contains("Richard") -> numRichard++
                    else -> println("no such winner")
                }
                when {
                    winner.winningHand.contains("high card") -> numHighCard++
                    winner.winningHand.contains("one pair") -> numOnePair++
                    winner.winningHand.contains("two pair") -> numTwoPair++
                    winner.winningHand.contains("three of a kind") -> numThreeKind++
                    winner.winningHand.contains("simple straight") -> numStraight++
                    winner.winningHand.contains("simple flush") -> numFlush++
                    winner.winningHand.contains("full house") -> numFullHouse++
                    winner.winningHand.contains("four of a kind") -> numFourKind++
                    winner.winningHand.contains("straight flush") -> numStraightFlush++
                    winner.winningHand.contains("royal flush") -> numRoyalFlush++
                    else -> println("NOT A POKER HAND TYPE")
                }
                println("|${winner.name}|${winner.winningHand}|${winner.status}|")
            }

            val pcntMike = doPcnt(numMike, numRuns)
            val pcntJan = doPcnt(numJan, numRuns)
            val pcntWally = doPcnt(numWally, numRuns)
            val pcntKevin = doPcnt(numKevin, numRuns)
            val pcntRichard = doPcnt(numRichard, numRuns)


            println("-------------- STATS -------------")
            println("|--- Mike won $numMike times [$pcntMike%]")
            println("|--- Jan won $numJan times [$pcntJan%]")
            println("|--- Wally won $numWally times [$pcntWally%]")
            println("|--- Kevin won $numKevin times [$pcntKevin%]")
            println("|--- Richard won $numRichard times [$pcntRichard%]")
            println("========================================")
            print("| high card: $numHighCard")
            print("| one pair: $numOnePair")
            print("| two pair: $numTwoPair")
            print("| three of a kind: $numThreeKind")
            print("| straight: $numStraight")
            print("| flush: $numFlush")
            print("| full house: $numFullHouse")
            print("| four of a kind: $numFourKind")
            print("| straight flush: $numStraightFlush")
            print("| royal flush: $numRoyalFlush")


        }

        start() // <--- this starts the whole show

    }
}