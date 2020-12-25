/*
 * Copyright (c) 2020. Michael D. Corbridge
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains the property of Michael D. Corbridge. The intellectual and technical concepts contained herein are proprietary to Michael D. Corbridge and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained from Michael D. Corbridge.
 */

package com.mcorbridge.kotlinfirebase.cards

class StraightFlushTied {
    /**
     * Five cards in a sequence, all of the same suit.
     * */

    companion object{
        var hand0:Hand = Hand()
        var hand1:Hand = Hand()
        var hand2:Hand = Hand()
        var hand3:Hand = Hand()
        var hand4:Hand = Hand()

        init{
            makeHands()
        }

        fun makeHands(){

            var card0 = Card("spade", "4", 4)
            hand0.cards.add(card0)
            var card1 = Card("spade", "5", 5)
            hand0.cards.add(card1)
            var card2 = Card("spade", "6", 6)
            hand0.cards.add(card2)
            var card3 = Card("spade", "7", 7)
            hand0.cards.add(card3)
            var card4 = Card("spade", "8", 8)
            hand0.cards.add(card4)


            var card10 = Card("heart", "4", 4)
            hand1.cards.add(card10)
            var card11 = Card("heart", "5", 5)
            hand1.cards.add(card11)
            var card12 = Card("heart", "6", 6)
            hand1.cards.add(card12)
            var card13 = Card("heart", "7", 7)
            hand1.cards.add(card13)
            var card14 = Card("heart", "8", 8)
            hand1.cards.add(card14)


            var card20 = Card("diamond", "4", 4)
            hand2.cards.add(card20)
            var card21 = Card("diamond", "5", 5)
            hand2.cards.add(card21)
            var card22 = Card("diamond", "6", 6)
            hand2.cards.add(card22)
            var card23 = Card("diamond", "7", 7)
            hand2.cards.add(card23)
            var card24 = Card("diamond", "8", 8)
            hand2.cards.add(card24)


            var card30 = Card("club", "4", 4)
            hand3.cards.add(card30)
            var card31 = Card("club", "5", 5)
            hand3.cards.add(card31)
            var card32 = Card("club", "6", 6)
            hand3.cards.add(card32)
            var card33 = Card("club", "7", 7)
            hand3.cards.add(card33)
            var card34 = Card("club", "8", 8)
            hand3.cards.add(card34)


            var card40 = Card("spade", "ace", 14)
            hand4.cards.add(card40)
            var card41 = Card("spade", "3", 3)
            hand4.cards.add(card41)
            var card42 = Card("spade", "3", 3)
            hand4.cards.add(card42)
            var card43 = Card("spade", "9", 9)
            hand4.cards.add(card43)
            var card44 = Card("spade", "king", 13)
            hand4.cards.add(card44)
        }





    }


}