package edu.uiowa

import edu.uiowa.View.GUI
import javafx.application.Application

/*** DRIVER FOR APPLICATION ***/
fun main(args: Array<String>) {
    Application.launch(GUI::class.java, *args)
}