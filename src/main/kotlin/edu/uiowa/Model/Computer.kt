package edu.uiowa.Model

import edu.uiowa.View.Square
import edu.uiowa.View.TYPE
import edu.uiowa.View.controller

/**
 *  Logic for how a computer views and picks moves from board
 *  Very basic computer 'ai'
 */

class Computer: Player {

    override var symbol: TYPE = TYPE.EMPTY

    val potentialMoves = mutableListOf<Square>()

    // determines open squares on board so no incorrect selections can be made
    fun openMoves(board: Array<Array<Square?>>){
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                if (board[i][j]!!.mark == TYPE.EMPTY) {
                    println("computers move: row:$i col:$j")
                   potentialMoves.add(Square(i, j, TYPE.EMPTY))
                }
            }
        }
        pickMove()
    }

    // places computers move to board
   private fun pickMove(){
       potentialMoves.shuffle()
       if(potentialMoves.size > 0) {
           controller.computerMove(potentialMoves[potentialMoves.size - 1])
           potentialMoves.clear()
       }
   }
}