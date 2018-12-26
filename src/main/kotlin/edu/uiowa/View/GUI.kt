package edu.uiowa.View

import edu.uiowa.*
import edu.uiowa.Model.Board
import edu.uiowa.Model.Computer
import edu.uiowa.Model.GAMESTATE
import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.stage.Stage

val canvas = Canvas(width, height)
val context: GraphicsContext = canvas.graphicsContext2D
val controller = Controller()

/**
 * Responsible for game flow and aggregating the controller, display, and board classes
 */
class GUI: Application() {

    override fun start(stage: Stage) {
        val view = Display()
        val model = Board(view)
        controller.setModel(model)

        //val fxmlRoot: Parent = load(javaClass.classLoader.getResource("gui.fxml"))
        //val scene = Scene(fxmlRoot)


        val root = Group()
        val scene = Scene(root)


        stage.title = "Tic Tac Toe"
        stage.scene = scene
        root.children += canvas

        val startMessage = "WOULD YOU LIKE TO PLAY TIC TAC TOE?"
        Alert(Alert.AlertType.INFORMATION, startMessage, ButtonType.YES, ButtonType.NO).showAndWait().ifPresent {
            view.displayBoard(context)
            model.reset()
        }
        /**
         * Handles the users clicks and talks with controller to validate user clicks
         */

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED) { event ->

            if (event.button == MouseButton.PRIMARY) {

                val x = event.x
                val y = event.y
                val col = (x / cellSize).toInt()
                val row = (y / cellSize).toInt()
                val cell = Square(row, col, model.currentPlayer.symbol)

                controller.moveRequest(cell)
                model.getWin()

                if(model.currentPlayer is Computer) {
                    (model.currentPlayer as Computer).openMoves(model.gameBoard)
                    model.getWin()
                }
                if (model.gameState == GAMESTATE.PLAY)
                    return@addEventHandler

                val message: String = when (model.gameState) {
                    GAMESTATE.TIE-> "DRAW"
                    GAMESTATE.XWIN -> "X Won"
                    GAMESTATE.OWIN -> "O Won"
                    else -> "?"
                }
                Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK).showAndWait().ifPresent {

                    model.reset()
                    view.displayBoard(context)
                }
            }
            start(stage)
        }
        stage.show()
    }
}