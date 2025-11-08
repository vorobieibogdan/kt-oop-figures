package mate.academy

import mate.academy.model.Color
import mate.academy.service.ColorSupplier
import kotlin.math.PI
import kotlin.random.Random

interface Figure {
    val color: Color
    fun calculateArea(): Double
    fun draw()
}

class Square(val side: Double, override val color: Color) : Figure {
    override fun calculateArea(): Double = side * side

    override fun draw() {
        println("Figure: square, area: ${calculateArea()} sq. units, side: $side units, color: $color")
    }
}

class Rectangle(val width: Double, val height: Double, override val color: Color) : Figure {
    override fun calculateArea(): Double = width * height

    override fun draw() {
        println("Figure: rectangle, area: ${calculateArea()} sq. units, width: $width, height: $height, color: $color")
    }
}

class RightTriangle(val firstLeg: Double, val secondLeg: Double, override val color: Color) : Figure {
    override fun calculateArea(): Double = (firstLeg * secondLeg) / 2

    override fun draw() {
        println("Figure: right triangle, area: ${calculateArea()} sq. units, firstLeg: $firstLeg, secondLeg: $secondLeg, color: $color")
    }
}

class Circle(val radius: Double, override val color: Color) : Figure {
    override fun calculateArea(): Double = PI * radius * radius

    override fun draw() {
        println("Figure: circle, area: ${calculateArea()} sq. units, radius: $radius units, color: $color")
    }
}

class IsoscelesTrapezoid(val baseA: Double, val baseB: Double, val height: Double, override val color: Color) : Figure {
    override fun calculateArea(): Double = (baseA + baseB) / 2 * height

    override fun draw() {
        println("Figure: isosceles trapezoid, area: ${calculateArea()} sq. units, baseA: $baseA, baseB: $baseB, height: $height, color: $color")
    }
}

class FigureSupplier(private val colorSupplier: ColorSupplier) {
    fun getRandomFigure(): Figure {
        val color = colorSupplier.getRandomColor()
        return when (Random.nextInt(5)) {
            0 -> Square(Random.nextDouble(1.0, 10.0), color)
            1 -> Rectangle(Random.nextDouble(1.0, 10.0), Random.nextDouble(1.0, 10.0), color)
            2 -> RightTriangle(Random.nextDouble(1.0, 10.0), Random.nextDouble(1.0, 10.0), color)
            3 -> Circle(Random.nextDouble(1.0, 10.0), color)
            else -> IsoscelesTrapezoid(Random.nextDouble(1.0, 10.0), Random.nextDouble(1.0, 10.0), Random.nextDouble(1.0, 10.0), color)
        }
    }

    fun getDefaultFigure(): Figure = Circle(10.0, Color.WHITE)
}

fun main() {
    val colorSupplier = ColorSupplier()
    val figureSupplier = FigureSupplier(colorSupplier)

    val figures = Array<Figure>(6) { index ->
        if (index < 3) figureSupplier.getRandomFigure() else figureSupplier.getDefaultFigure()
    }

    figures.forEach { it.draw() }
}
