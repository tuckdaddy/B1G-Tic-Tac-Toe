package edu.uiowa.Model

import edu.uiowa.View.Display
import edu.uiowa.View.Square

/**
 *  Interface for the model of the board to help with communication from the controller and updating the model
 */
interface ModelInterface {
    var gameBoard: Array<Array<Square?>>
    var view: Display
    var p1: Player
    var p2: Player
    fun movePlayer(s: Square)
}