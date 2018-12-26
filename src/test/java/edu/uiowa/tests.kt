package edu.uiowa

import edu.uiowa.Model.Board
import edu.uiowa.Model.GAMESTATE
import edu.uiowa.View.Display
import edu.uiowa.View.Square
import edu.uiowa.View.TYPE
import junit.framework.Assert.assertEquals
import org.junit.Test

class tests {
    val c = Controller()
    val view = Display(c)
    val model = Board(view)
    val set = c.setModel(model)
    @Test
    fun place1Symbol(){
        model.movePlayer(Square(1, 2, TYPE.X))
        model.movePlayer(Square(0, 2, TYPE.X))
        model.movePlayer(Square(2, 2, TYPE.X))
        assertEquals(GAMESTATE.XWIN, model.getWin())



    }
    @Test
    fun validMove(){
        //model.

    }
//I DIDNT MAKE THIS PROJECT VERY TEST FRIENDLY
}