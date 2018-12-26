package edu.uiowa.View

import javafx.scene.canvas.GraphicsContext

const val cellSize = 200.0
const val size = 3
const val width= size * cellSize
const val height = size * cellSize
const val lineWidth = 6.0
const val padding = 20.0

/**
  *  Square is used as a data class to easily look up where a square is placed and its current mark on the game board
  *  This helps as a conduit for the board class to keep track of the board and for the Display class to update the correct cell
  *  clicked by the user.
 */
data class Square(val row:Int, val col:Int, var mark: TYPE) {

    // paints mark on the coordinates of the square who called paintMark
    fun paintMark(g:GraphicsContext){
        when(mark){
            TYPE.X ->{
                g.stroke = javafx.scene.paint.Color.YELLOW
                g.lineWidth = lineWidth
                g.strokeLine(col * cellSize + padding, row * cellSize + padding,(col +1 )* cellSize - padding,(row +1) * cellSize - padding)
                g.strokeLine((col +1) * cellSize - padding, row * cellSize + padding, col * cellSize + padding, (row +1) * cellSize - padding)
            }
            TYPE.O ->{
                g.stroke = javafx.scene.paint.Color.WHITE
                g.lineWidth = lineWidth
                g.strokeOval(col * cellSize + padding, row* cellSize + padding, cellSize -2 * padding, cellSize -2* padding)
            }
            else ->{
                /** only way computers move will print  ***/
                //g.stroke = javafx.scene.paint.Color.WHITE
               // g.lineWidth = lineWidth
                //g.strokeOval(col * cellSize + padding, row* cellSize + padding, cellSize -2 * padding, cellSize -2* padding)
                g.fill()
            }
        }
    }
}
//represents the states a square can be. Helpful in determining squares availability
enum class TYPE{EMPTY,X,O}