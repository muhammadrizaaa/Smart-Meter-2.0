package org.riza0004.smartmeter20.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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
import org.riza0004.smartmeter20.ui.theme.SmartMeter20Theme

@Composable
fun UsageReport(
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.usage_report),
                style = MaterialTheme.typography.headlineSmall
            )
            Icon(
                painter = painterResource(R.drawable.baseline_keyboard_arrow_right_24),
                contentDescription = stringResource(R.string.show_more),
                tint = colorResource(R.color.black),
                modifier = Modifier.size(24.dp)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    modifier = Modifier.padding(end = 8.dp),
                    text = stringResource(R.string.energy, 0.04),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = stringResource(R.string.power, 16),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Dropdown()
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                modifier = Modifier.padding(end = 8.dp),
                text = stringResource(R.string.outcome, 50000),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Box(
            modifier = Modifier.fillMaxWidth()
        ){
            LineChart()
        }
    }
}

@Composable
fun LineChart(
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier.height(150.dp)
){
    val pointsData: List<Point> = listOf(
        Point(0f, 40f),
        Point(1f, 100f),
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
    val steps = 4
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
                    lineStyle = LineStyle(),
                    intersectionPoint = IntersectionPoint(),
                    selectionHighlightPoint = SelectionHighlightPoint(),
                    shadowUnderLine = ShadowUnderLine(),
                    selectionHighlightPopUp = SelectionHighlightPopUp()
                )
            )
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(),
        backgroundColor = colorResource(R.color.light_main)
    )
    LineChart(
        modifier = modifier.fillMaxWidth(),
        lineChartData = lineChartData
    )
}

@Preview(showBackground = true)
@Composable
fun UsageReportPreview() {
    SmartMeter20Theme {
        UsageReport()
    }
}