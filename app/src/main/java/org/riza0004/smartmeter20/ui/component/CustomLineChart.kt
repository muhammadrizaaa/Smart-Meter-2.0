package org.riza0004.smartmeter20.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import org.riza0004.smartmeter20.R

@Composable
fun CustomLineChart(
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier.height(300.dp),
    steps: Int
){
    val pointsData: List<Point> = listOf(
        Point(0f, 40f),
        Point(1f, 150f),
        Point(2f, 25f),
        Point(3f, 75f),
        Point(4f, 10f)
    )
    val xAxisData = AxisData.Builder()
        .axisStepSize(75.dp)
        .backgroundColor(colorResource(R.color.light_main))
        .steps(pointsData.size - 1)
        .labelData { i -> i.toString() }
        .labelAndAxisLinePadding(16.dp)
        .build()

    val maxY = pointsData.maxOf { it.y }
    val steps = steps
    val yScale = maxY/steps

    val yAxisData = AxisData.Builder()
        .steps(steps)
        .backgroundColor(colorResource(R.color.light_main))
        .labelAndAxisLinePadding(24.dp)
        .labelData { i ->
            (i * yScale).toInt().toString()
        }.build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    lineStyle = LineStyle(
                        color = colorResource(R.color.main)
                    ),
                    intersectionPoint = IntersectionPoint(
                        colorResource(R.color.main)
                    ),
                    selectionHighlightPoint = SelectionHighlightPoint(
                        color = colorResource(R.color.main_background)
                    ),
                    shadowUnderLine = ShadowUnderLine(),
                    selectionHighlightPopUp = SelectionHighlightPopUp()
                )
            )
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(
            colorResource(R.color.white)
        ),
        backgroundColor = colorResource(R.color.light_main)
    )
    LineChart(
        modifier = modifier.fillMaxWidth(),
        lineChartData = lineChartData
    )
}