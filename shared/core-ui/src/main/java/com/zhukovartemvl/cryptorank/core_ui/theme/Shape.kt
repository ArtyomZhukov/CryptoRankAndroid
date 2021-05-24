package com.zhukovartemvl.cryptorank.core_ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp


val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

class TriangleShape(val direction: TriangleShapeDirection) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val trianglePath = Path().apply {
            when (direction) {
                TriangleShapeDirection.Bottom -> {
                    moveTo(x = size.width / 2f, y = 0f)
                    lineTo(x = size.width, y = size.height)
                    lineTo(x = 0f, y = size.height)
                }
                TriangleShapeDirection.Top -> {
                    moveTo(x = 0f, y = 0f)
                    lineTo(x = size.width, y = 0f)
                    lineTo(x = size.width / 2f, y = size.height)
                }
            }

        }
        return Outline.Generic(path = trianglePath)
    }
}

sealed class TriangleShapeDirection {
    object Top : TriangleShapeDirection()
    object Bottom : TriangleShapeDirection()
}
