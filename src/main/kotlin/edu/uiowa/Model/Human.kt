package edu.uiowa.Model

import edu.uiowa.View.Square
import edu.uiowa.View.TYPE

/**
 *  Pretty much useless
 */
class Human: Player {

    var name = " "
    override var symbol: TYPE = TYPE.EMPTY
    // sets players name
     fun playerName(n:String){
        name = n
    }

}