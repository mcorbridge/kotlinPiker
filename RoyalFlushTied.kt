/*
 * Copyright (c) 2020. Michael D. Corbridge
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains the property of Michael D. Corbridge. The intellectual and technical concepts contained herein are proprietary to Michael D. Corbridge and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained from Michael D. Corbridge.
 */

package com.mcorbridge.kotlinfirebase.cards

class RoyalFlushTied {
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

            var card0 = Card("spade", "ace", 14)
            hand0.cards.add(card0)
            var card1 = Card("spade", "king", 13)
            hand0.cards.add(card1)
            var card2 = Card("spade", "queen", 12)
            hand0.cards.add(card2)
            var card3 = Card("spade", "jack", 11)
            hand0.cards.add(card3)
            var card4 = Card("spade", "10", 10)
            hand0.cards.add(card4)


            var card10 = Card("heart", "ace", 14)
            hand1.cards.add(card10)
            var card11 = Card("heart", "king", 13)
            hand1.cards.add(card11)
            var card12 = Card("heart", "queen", 12)
            hand1.cards.add(card12)
            var card13 = Card("heart", "jack", 11)
            hand1.cards.add(card13)
            var card14 = Card("heart", "10", 10)
            hand1.cards.add(card14)


            var card20 = Card("diamond", "ace", 14)
            hand2.cards.add(card20)
            var card21 = Card("diamond", "king", 13)
            hand2.cards.add(card21)
            var card22 = Card("diamond", "queen", 12)
            hand2.cards.add(card22)
            var card23 = Card("diamond", "jack", 11)
            hand2.cards.add(card23)
            var card24 = Card("diamond", "10", 10)
            hand2.cards.add(card24)


            var card30 = Card("club", "ace", 14)
            hand3.cards.add(card30)
            var card31 = Card("club", "king", 13)
            hand3.cards.add(card31)
            var card32 = Card("club", "queen", 12)
            hand3.cards.add(card32)
            var card33 = Card("club", "jack", 11)
            hand3.cards.add(card33)
            var card34 = Card("club", "10", 10)
            hand3.cards.add(card34)


            var card40 = Card("spade", "2", 2)
            hand4.cards.add(card40)
            var card41 = Card("club", "3", 3)
            hand4.cards.add(card41)
            var card42 = Card("diamond", "6", 6)
            hand4.cards.add(card42)
            var card43 = Card("heart", "9", 9)
            hand4.cards.add(card43)
            var card44 = Card("spade", "8", 8)
            hand4.cards.add(card44)
        }





    }


}