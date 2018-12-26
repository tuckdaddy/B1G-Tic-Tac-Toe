package edu.uiowa

import edu.uiowa.Model.Board
import edu.uiowa.View.Square
import javafx.scene.input.MouseEvent

/**
 * The Controller takes input from the user and requests the board to reflect users selection.
 */
class Controller {

     private lateinit var model: Board

     fun setModel(m: Board) {
         model = m
     }

     //user move is sent to the board to check its validity
     fun moveRequest(position: Square) {
         model.movePlayer(position)
     }

     //computer move is sent to the board to check its validity
     fun computerMove(position: Square) {
         model.computerMove(position)

     }


     /***                    FXML                    ***/

     fun startGame(mouseEvent: MouseEvent) {

     }

     fun quitGame(mouseEvent: MouseEvent) {

     }

     fun pvp(mouseEvent: MouseEvent) {

     }

     fun cvp(mouseEvent: MouseEvent) {

     }

    fun handleClick(mouseEvent: MouseEvent) {


        val x = mouseEvent.sceneX.toInt()
        val y = mouseEvent.sceneY.toInt()

        val col = (x / 200)
        val row =( y / 200)
        //model.movePlayer(position)
    }
}
