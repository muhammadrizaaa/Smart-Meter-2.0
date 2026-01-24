package org.riza0004.smartmeter20.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
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
fun DetailedUsageReport(
    modifier: Modifier = Modifier
){
    val data = listOf(
        "Harian",
        "Bulanan",
        "Per-jam"
    )
    var selectedData by remember { mutableStateOf(data[0]) }
    var isExpanded by remember { mutableStateOf(false) }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.usage_report),
            style = MaterialTheme.typography.headlineSmall
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(end = 8.dp),
                text = stringResource(R.string.outcome, 500000),
                style = MaterialTheme.typography.bodyMedium
            )
            Dropdown(
                isExpanded = isExpanded,
                data = data,
                onClick = { isExpanded = !isExpanded },
                onCLose = {
                    isExpanded = false
                },
                onSelect = {
                    selectedData = it
                },
                selectedData = selectedData
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                modifier = Modifier.padding(end = 8.dp),
                text = stringResource(R.string.power, formatDecimal(20.toDouble())),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = stringResource(R.string.current, formatDecimal(0.2)),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                modifier = Modifier.padding(end = 8.dp),
                text = stringResource(R.string.energy, formatDecimal(0.002)),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = stringResource(R.string.voltage, formatDecimal(200.toDouble())),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        DetailedLineChart()
    }
}

@Composable
fun DetailedLineChart(
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier.height(300.dp)
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
    val steps = 8
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
fun DetailedUsageReportPreview() {
    SmartMeter20Theme {
        DetailedUsageReport()
    }
}