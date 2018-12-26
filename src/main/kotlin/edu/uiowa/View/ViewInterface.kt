package edu.uiowa.View

import edu.uiowa.Model.GAMESTATE
import edu.uiowa.Model.Player
import edu.uiowa.View.Square

/**
 * Purpose of ViewInterface is to help communication between the engine (model) and the GUI
 */
interface ViewInterface {
    fun playerMove(square: Square)
    fun tie(): GAMESTATE
    fun win(p: Player): GAMESTATE
}
