/*
 * Copyright (c) 2020. Michael D. Corbridge
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains the property of Michael D. Corbridge. The intellectual and technical concepts contained herein are proprietary to Michael D. Corbridge and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained from Michael D. Corbridge.
 */

package com.mcorbridge.kotlinfirebase.cards

class Cards {}

//class Card(val suit: String, val name: String, val value: Int, var pokerValue: String="default")
class Card(val suit: String, val name: String, val value: Int)

    class Hearts {
        var hearts: MutableList<Card> = mutableListOf(
            Card("heart", "2", 2),
            Card("heart", "3", 3),
            Card("heart", "4", 4),
            Card("heart", "5", 5),
            Card("heart", "6", 6),
            Card("heart", "7", 7),
            Card("heart", "8", 8),
            Card("heart", "9", 9),
            Card("heart", "10", 10),
            Card("heart", "jack", 11),
            Card("heart", "queen", 12),
            Card("heart", "king", 13),
            Card("heart", "ace", 14)
        )
    }

    class Spades {
        var spades: MutableList<Card> = mutableListOf(
            Card("spade", "2", 2),
            Card("spade", "3", 3),
            Card("spade", "4", 4),
            Card("spade", "5", 5),
            Card("spade", "6", 6),
            Card("spade", "7", 7),
            Card("spade", "8", 8),
            Card("spade", "9", 9),
            Card("spade", "10", 10),
            Card("spade", "jack", 11),
            Card("spade", "queen", 12),
            Card("spade", "king", 13),
            Card("spade", "ace", 14)
        )
    }

    class Diamonds {
        var diamonds: MutableList<Card> = mutableListOf(
            Card("diamond", "2", 2),
            Card("diamond", "3", 3),
            Card("diamond", "4", 4),
            Card("diamond", "5", 5),
            Card("diamond", "6", 6),
            Card("diamond", "7", 7),
            Card("diamond", "8", 8),
            Card("diamond", "9", 9),
            Card("diamond", "10", 10),
            Card("diamond", "jack", 11),
            Card("diamond", "queen", 12),
            Card("diamond", "king", 13),
            Card("diamond", "ace", 14)
        )
    }

    class Clubs {
        var clubs: MutableList<Card> = mutableListOf(
            Card("club", "2", 2),
            Card("club", "3", 3),
            Card("club", "4", 4),
            Card("club", "5", 5),
            Card("club", "6", 6),
            Card("club", "7", 7),
            Card("club", "8", 8),
            Card("club", "9", 9),
            Card("club", "10", 10),
            Card("club", "jack", 11),
            Card("club", "queen", 12),
            Card("club", "king", 13),
            Card("club", "ace", 14)
        )
    }

