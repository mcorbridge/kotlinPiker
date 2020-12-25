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

    private var numMike:Int = 0
    private var numJan:Int = 0
    private var numWally:Int = 0
    private var numKevin:Int = 0
    private var numRichard:Int = 0
    private var numRuns:Int = 9 // val of zero (0) means the simulation runs ONCE

    fun doPcnt(numPlayerWins:Int, numRuns:Int):String{
        val decimalFormat = DecimalFormat("##.#")
        decimalFormat.roundingMode = RoundingMode.UP
        return decimalFormat.format((numPlayerWins.toDouble() / (numRuns.toDouble() + 1.0)) * 100)
    }

    init{

        fun start(){

            for(n in 0..numRuns)run {
                var winner:Winner = PokerGame.findWinner() // each players hand has been dealt at this point
                when{
                    winner.name.contains("Mike") -> numMike++
                    winner.name.contains("Jan") -> numJan++
                    winner.name.contains("Wally") -> numWally++
                    winner.name.contains("Kevin") -> numKevin++
                    winner.name.contains("Richard") -> numRichard++
                    else -> println("no such winner")
                }
                println("${winner.name} won with ${winner.winningHand} type: ${winner.status}")
            }

            var pcntMike = doPcnt(numMike, numRuns)
            var pcntJan = doPcnt(numJan, numRuns)
            var pcntWally = doPcnt(numWally, numRuns)
            var pcntKevin = doPcnt(numKevin, numRuns)
            var pcntRichard = doPcnt(numRichard, numRuns)

            println("Mike won $numMike times [$pcntMike%]")
            println("Jan won $numJan times [$pcntJan%]")
            println("Wally won $numWally times [$pcntWally%]")
            println("Kevin won $numKevin times [$pcntKevin%]")
            println("Richard won $numRichard times [$pcntRichard%]")
        }

        start() // <--- this starts the whole show

    }
}