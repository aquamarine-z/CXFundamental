package cxplugins.cxfundamental.minecraft.swing.dsl

import cxplugins.cxfundamental.minecraft.command.CPMLCommandExecutor
import cxplugins.cxfundamental.minecraft.swing.SwingFrame


fun SwingFrame.Companion.createWindow() {

}

fun test() {
    val frame = SwingFrame("1", 6)
    frame.setContent {
        multipagePanel {
            newPage {
                button(0, 0) {
                    title("title")
                    amount(1)
                    onLeftClick {
                        println("Clicked")
                        title("1")
                    }
                }
            }
            buttonArea {
                button(0, 0) {
                    title("")
                    description("")
                    amount(1)
                    onLeftClick {
                    }
                }
            }
        }
    }

    CPMLCommandExecutor.register {
        command = "cxp test"
        description = ""
        parameter {
            int {
                name = "a"
                calculate = true
            }
            string {
                name = "b"
            }
            player {
                name = "c"
            }
        }
        action {
            integers["a"]
            strings["b"]
            players["c"]
            true
        }
    }
}