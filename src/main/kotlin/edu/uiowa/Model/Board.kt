package edu.uiowa.Model

import edu.uiowa.View.*

/**
 *  Representation of the board. Contains logic for determining valid player moves, whose turn and who wins.
 *  The engine of the application. Implements ModelInterface to enforce the minimal requirements for a tic tac toe game
 */
class Board(v: Display) : ModelInterface {

    override var gameBoard:Array<Array<Square?>> = array2d(sizeOuter = 3, sizeInner = 3) { null }
    var gameState: GAMESTATE = GAMESTATE.LIMBO
    override var view : Display = v
    override var p1: Player = Human()
    override var p2: Player = Human()
    private var comp = Computer()
    var currentPlayer: Player
    private var gameTYPE: VERSUS = VERSUS.PvP

    //constructor
    init {
        for (row in 0..2) {
            for (col in 0..2) {
                gameBoard[row][col] = Square(row, col, TYPE.EMPTY)
            }
        }
        currentPlayer = p1
        gameState = GAMESTATE.PLAY
        when(gameTYPE){
            VERSUS.PvP ->{
                p1.symbol = TYPE.X
                p2.symbol = TYPE.O
                (p1 as Human).playerName("Jeff")
                (p2 as Human).playerName("Jim")
            }
            VERSUS.PvC ->{
                p2 = comp
                (p1 as Human).playerName("Jeff")
                p1.symbol = TYPE.X
                p2.symbol = TYPE.O
            }
        }
    }
    //places only valid moves onto the model of board acts as a gate keeper for place move 
     override fun movePlayer(s: Square) {
        if(gameBoard[s.row][s.col]!!.mark == TYPE.EMPTY){
            // places the symbol on the board model
            gameBoard[s.row][s.col]!!.mark = currentPlayer.symbol

            placeMove(s)

            // binary number used to calculate the winning pattern
            val bitP = s.row * size + s.col
            if (currentPlayer.symbol == TYPE.X)
                xPattern = xPattern or (1 shl bitP)
            else
                oPattern = oPattern or (1 shl bitP)
        }
    }
    //places valid computer moves to the board
    fun computerMove(s: Square){
        gameBoard[s.row][s.col]!!.mark = currentPlayer.symbol
        println("this is computers symbol ${currentPlayer.symbol}")
        view.playerMove(s)
        switchPlayer()
    }

   //talks to the display class, displaying the correct symbol on user selected square 
    private fun placeMove(location: Square){
        view.playerMove(location)
        switchPlayer()
    }
    //controls current player
    private fun switchPlayer(){ currentPlayer = if(p1 == currentPlayer ){ p2 } else{ p1 } }

    //logic for determining winner or tie
    fun getWin(){
        val currentPatter = if (currentPlayer.symbol == TYPE.X)xPattern else oPattern

        if (winPattern.any{it and currentPatter ==it})
            gameState = view.win(currentPlayer)

        if(gameBoard.sumBy { it.count { it!!.mark== TYPE.EMPTY } } == 0) {

            gameState = if (winPattern.any { it and currentPatter == it })
                view.win(currentPlayer)
            else
                view.tie()
        }
    }
    //resets model of tic tac toe game
    fun reset() {
        gameBoard.forEach { it.forEach { it!!.mark = TYPE.EMPTY} }
        gameBoard.forEach { it.forEach { it!!.paintMark(context) } }
        gameState = GAMESTATE.PLAY
        oPattern = 0
        xPattern = 0

    }
    //initialize board with winning patterns for easy checking of the game state
    companion object {

        private var oPattern = 0
        private var xPattern = 0

        val winPattern = listOf(
                0b000_000_111,
                0b000_111_000,
                0b111_000_000,

                0b001_001_001,
                0b010_010_010,
                0b100_100_100,

                0b100_010_001,
                0b001_010_100 //diagonal win
        )
    }
}
// constraints to control game flow
enum class GAMESTATE{LIMBO,TIE,PLAY,XWIN,OWIN}
// game types
enum class VERSUS {PvP,PvC}
// data structure for a 2d array
inline fun <reified Square> array2d(sizeOuter: Int, sizeInner: Int, noinline innerInit: (Int)->Square): Array<Array<Square>>
        = Array(sizeOuter) { Array(sizeInner, innerInit) }

