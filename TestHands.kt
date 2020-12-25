/*
 * Copyright (c) 2020. Michael D. Corbridge
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains the property of Michael D. Corbridge. The intellectual and technical concepts contained herein are proprietary to Michael D. Corbridge and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained from Michael D. Corbridge.
 */

package com.mcorbridge.kotlinfirebase.cards

class TestHands {

    class TestRoyalFlush {
        var hand = Hand()

        init {
            val card0 = Card("diamond", "10", 10, )
            hand.cards.add(card0)

            val card1 = Card("diamond", "jack", 11)
            hand.cards.add(card1)

            val card2 = Card("diamond", "queen", 12)
            hand.cards.add(card2)

            val card3 = Card("diamond", "king", 13)
            hand.cards.add(card3)

            val card4 = Card("diamond", "ace", 14)
            hand.cards.add(card4)
        }
    }

    class TestStraightFlush {
        var hand = Hand()

        init {
            val card0 = Card("diamond", "7", 7)
            hand.cards.add(card0)

            val card1 = Card("diamond", "8", 8)
            hand.cards.add(card1)

            val card2 = Card("diamond", "9", 9)
            hand.cards.add(card2)

            val card3 = Card("diamond", "10", 10)
            hand.cards.add(card3)

            val card4 = Card("diamond", "jack", 11)
            hand.cards.add(card4)

        }
    }

    class TestFourOfKind {
        var hand = Hand()

        init {
            val card0 = Card("diamond", "7", 7)
            hand.cards.add(card0)

            val card1 = Card("club", "7", 7)
            hand.cards.add(card1)

            val card2 = Card("heart", "7", 7)
            hand.cards.add(card2)

            val card3 = Card("spade", "7", 7)
            hand.cards.add(card3)

            val card4 = Card("diamond", "jack", 11)
            hand.cards.add(card4)

        }
    }

    class TestFullHouse {
        var hand = Hand()

        init {
            val card0 = Card("diamond", "7", 7)
            hand.cards.add(card0)

            val card1 = Card("club", "7", 7)
            hand.cards.add(card1)

            val card2 = Card("heart", "jack", 11)
            hand.cards.add(card2)

            val card3 = Card("spade", "jack", 11)
            hand.cards.add(card3)

            val card4 = Card("diamond", "jack", 11)
            hand.cards.add(card4)

        }
    }

    class TestFlush {
        var hand = Hand()

        init {
            val card0 = Card("diamond", "2", 2)
            hand.cards.add(card0)

            val card1 = Card("diamond", "7", 7)
            hand.cards.add(card1)

            val card2 = Card("diamond", "jack", 11)
            hand.cards.add(card2)

            val card3 = Card("diamond", "king", 13)
            hand.cards.add(card3)

            val card4 = Card("diamond", "ace", 14)
            hand.cards.add(card4)

        }
    }

    class TestStraight {
        var hand = Hand()

        init {
            val card0 = Card("diamond", "4", 4)
            hand.cards.add(card0)

            val card1 = Card("club", "5", 5)
            hand.cards.add(card1)

            val card2 = Card("spade", "6", 6)
            hand.cards.add(card2)

            val card3 = Card("heart", "7", 7)
            hand.cards.add(card3)

            val card4 = Card("diamond", "8", 8)
            hand.cards.add(card4)

        }
    }

    class TestThreeOfKind {
        var hand = Hand()

        init {
            val card0 = Card("diamond", "4", 4)
            hand.cards.add(card0)

            val card1 = Card("club", "4", 4)
            hand.cards.add(card1)

            val card2 = Card("spade", "4", 4)
            hand.cards.add(card2)

            val card3 = Card("heart", "7", 7)
            hand.cards.add(card3)

            val card4 = Card("diamond", "8", 8)
            hand.cards.add(card4)

        }
    }

    class TestTwoPair {
        var hand = Hand()

        init {
            val card0 = Card("diamond", "9", 9)
            hand.cards.add(card0)

            val card1 = Card("spade", "king", 13)
            hand.cards.add(card1)

            val card2 = Card("club", "9", 9)
            hand.cards.add(card2)

            val card3 = Card("diamond", "8", 8)
            hand.cards.add(card3)

            val card4 = Card("heart", "king", 13)
            hand.cards.add(card4)

        }
    }

    class TestPair {
        var hand = Hand()

        init {
            val card0 = Card("diamond", "ace", 14)
            hand.cards.add(card0)

            val card1 = Card("spade", "9", 9)
            hand.cards.add(card1)

            val card2 = Card("club", "9", 9)
            hand.cards.add(card2)

            val card3 = Card("diamond", "8", 8)
            hand.cards.add(card3)

            val card4 = Card("heart", "king", 13)
            hand.cards.add(card4)

        }
    }
}