package cxplugins.cxfundamental.minecraft.swing.windows

import cxplugins.cxfundamental.minecraft.math.geometry.Vector2I
import cxplugins.cxfundamental.minecraft.swing.SwingButton
import cxplugins.cxfundamental.minecraft.swing.SwingFrame
import cxplugins.cxfundamental.minecraft.swing.dsl.*
import org.bukkit.Material

class SwingInputWindow(title: String = "Input") : SwingFrame(title, 6) {
    private var textContent = ""

    class InputButton(var title: String, location: Vector2I) : SwingButton(location) {
        init {
            title(title)
            type(Material.SLIME_BALL)
            amount(1)
            onLeftClick {

            }
        }
    }

    init {
        setContent {
            itemHolder(4, 0) {
                type(Material.GRASS)
                title(textContent)
                amount(1)
            }
            multipagePanel(0, 1, 9, 5) {
                newPage {
                    this.setComponent(InputButton("0"))
                }
            }
        }
    }
}