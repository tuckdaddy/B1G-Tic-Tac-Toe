package edu.uiowa.View

import edu.uiowa.Model.GAMESTATE
import edu.uiowa.Model.Player
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color
/**
 * Responsible for displaying the user board and has to conform to the ViewInterface that enforces
 * player moves and the change of game states
 */

class Display : ViewInterface {

    //displays board to user
    fun displayBoard(g: GraphicsContext) {

        g.fill = Color(33/255.0,33/255.0, 33/255.0, 1.0)
        g.fillRect(0.0, 0.0, width, height)

        repeat(size) { j ->
            g.stroke = javafx.scene.paint.Color.DARKGRAY
            g.lineWidth = lineWidth
            g.strokeLine(j * cellSize, 0.0, j * cellSize, size * cellSize)
        }
        repeat(size) { i ->
            g.stroke = javafx.scene.paint.Color.DARKGRAY
            g.lineWidth = lineWidth
            g.strokeLine(0.0, i * cellSize, size * cellSize, i * cellSize)
        }
    }

    // places player move on correct square
    override fun playerMove(square: Square) {
        square.paintMark(context)
    }
    //determines player who won and changes game state
    override fun win(p: Player): GAMESTATE {
        return if(p.symbol != TYPE.X) GAMESTATE.XWIN
        else GAMESTATE.OWIN
    }
    //changes game state to tie
    override fun tie(): GAMESTATE {
        return GAMESTATE.TIE
    }
}


